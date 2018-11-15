package com.edu.smu.track2career.beans;

import com.edu.smu.track2career.entity.Course;
import com.edu.smu.track2career.entity.Skill;
import com.edu.smu.track2career.entity.Track;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.edu.smu.track2career.manager.PersistenceManager;
import java.util.ArrayList;
import java.util.Iterator;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

@ManagedBean(name = "search", eager = true)
@RequestScoped
public class SearchBean {

    public String queryJob;
//    public String queryTrack;
    public String messageJ;
    public String messageT;
//    public List<Track> tracks;
    public String track;
    public List<Track> initialTracks;

    public SearchBean() {
        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        try {
            TypedQuery<Track> query = em.createQuery("SELECT t FROM Track t", Track.class);
            initialTracks = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        em.close();
        track = "";
    }

    public String getQueryJob() {
        return queryJob;
    }

    public void setQueryJob(String queryJob) {
        this.queryJob = queryJob;
    }

    public String getMessageJ() {
        return messageJ;
    }

    public void setMessageJ(String messageJ) {
        this.messageJ = messageJ;
    }

    public String getMessageT() {
        return messageT;
    }

    public void setMessageT(String messageT) {
        this.messageT = messageT;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public List<Track> getInitialTracks() {
        return initialTracks;
    }

    public void setInitialTracks(List<Track> initialTracks) {
        this.initialTracks = initialTracks;
    }

    public void submitJob() {

    }

    public void submitTrack() {
        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        try {
            Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
            TypedQuery<Course> courseQuery = em.createQuery("SELECT c FROM Course c INNER JOIN c.trackId t WHERE t.trackName = ?1 AND c.trackId.trackId = t.trackId", Course.class);
            courseQuery.setParameter(1, track);

            List<Course> courseList = courseQuery.getResultList();

            TypedQuery<Skill> skillQuery = em.createQuery("SELECT s FROM Course c INNER JOIN c.trackId t INNER JOIN c.skillCollection s WHERE t.trackName = ?1 AND c.trackId.trackId = t.trackId", Skill.class);
            skillQuery.setParameter(1, track);

            List<Skill> skillList = skillQuery.getResultList();

            UserBean ub = (UserBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            String userId = ub.getUser().getUserId();
            
            TypedQuery<String> userSkillsQuery = em.createQuery("SELECT s.skillName FROM UserCourse uc INNER JOIN uc.courseId c INNER JOIN c.skillCollection s WHERE uc.userId.userId = ?1", String.class);
            userSkillsQuery.setParameter(1, userId);

            List<String> userSkills = userSkillsQuery.getResultList();
            
            Iterator<Skill> iterator = skillList.iterator();
            List<Skill> ownedSkills = new ArrayList<>();
            while (iterator.hasNext()) {
                Skill skill = iterator.next();
                String skillName = skill.getSkillName();
                if (userSkills.contains(skillName)) {
                    ownedSkills.add(skill);
                    iterator.remove();
                }
            }
            
            
            ArrayList<ArrayList<Skill>> unachievedList = new ArrayList<>();
            int count = 0;
            ArrayList<Skill> temp = null;
            for (int i = 0; i < skillList.size(); i++) {
                if (count == 4) {
                    unachievedList.add(temp);
                    count = 0;
                }
                if (count == 0) {
                    temp = new ArrayList<>();
                }
                temp.add(skillList.get(i));
                count++;
            }
            if (temp != null && !temp.isEmpty() && !unachievedList.contains(temp)) {
                unachievedList.add(temp);
            }
            
            
            
            ArrayList<ArrayList<Skill>> achievedList = new ArrayList<>();
            count = 0;
            temp = null;
            for (int i = 0; i < ownedSkills.size(); i++) {
                if (count == 4) {
                    achievedList.add(temp);
                    count = 0;
                }
                if (count == 0) {
                    temp = new ArrayList<>();
                }
                temp.add(ownedSkills.get(i));
                count++;
            }
            if (temp != null && !temp.isEmpty() && !achievedList.contains(temp)) {
                achievedList.add(temp);
            }
            
            flash.put("courses", courseList);
            flash.put("no", courseList.size());
            flash.put("track", track);
            flash.put("unachievedList", unachievedList);
            flash.put("achievedList", achievedList);
            FacesContext.getCurrentInstance().getExternalContext().redirect("TrackSearchResults.jsf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
