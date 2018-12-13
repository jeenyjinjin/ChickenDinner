package com.edu.smu.track2career.beans;

import com.edu.smu.track2career.entity.Course;
import com.edu.smu.track2career.entity.Custom;
import com.edu.smu.track2career.entity.Track;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.edu.smu.track2career.manager.PersistenceManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;
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
            
            
            
            // Retrieve all courses in the specified track
            TypedQuery<Course> courseQuery = em.createQuery("SELECT c FROM Course c INNER JOIN c.trackId t WHERE t.trackName = ?1 AND c.trackId.trackId = t.trackId", Course.class);
            courseQuery.setParameter(1, track);

            List<Course> courseList = courseQuery.getResultList();
            
            
            
            // Retrieve all skills for all courses in the specified track
            TypedQuery<String> skillQuery = em.createQuery("SELECT s.skillName FROM Course c INNER JOIN c.trackId t INNER JOIN c.skillCollection s WHERE t.trackName = ?1 AND c.trackId.trackId = t.trackId", String.class);
            skillQuery.setParameter(1, track);

            List<String> skillList = skillQuery.getResultList();

            
            
            // Count number of times each skills appeared
            TreeMap<String, Integer> map = new TreeMap<>();
            Iterator<String> skillIter = skillList.iterator();
            while (skillIter.hasNext()) {
                String skillName = skillIter.next();
                Integer num = 1;
                if (map.containsKey(skillName)) {
                    num = map.get(skillName) + 1;
                }
                map.put(skillName, num);                
            }
            
            
            
            // Retrieve userbean session
            UserBean ub = (UserBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            String userId = ub.getUser().getUserId();
            
            
            
            // Retrieve all skills that the authenticated user has
            TypedQuery<String> userSkillsQuery = em.createQuery("SELECT s.skillName FROM UserCourse uc INNER JOIN uc.courseId c INNER JOIN c.skillCollection s WHERE uc.userId.userId = ?1", String.class);
            userSkillsQuery.setParameter(1, userId);

            List<String> userSkills = userSkillsQuery.getResultList();
            
            
            // Iterate through the user skills and remove those that user has from the map
            List<String> ownedSkills = new ArrayList<>();
            for (String skillName : userSkills) {
                if (map.containsKey(skillName)) {
                    ownedSkills.add(skillName);
                    map.remove(skillName);
                }
            }
            
            
            // Arrange the skills in each arraylist of 4 for achieved skills
            ArrayList<ArrayList<String>> achievedList = new ArrayList<>();
            int count = 0;
            ArrayList<String> temp = null;
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
            
            
            // Arrange the rest of the elements in the map in accordance of count of appearance
            TreeMap<Integer, ArrayList<String>> tempHolder = new TreeMap<>();
            Iterator<String> mapIter = map.keySet().iterator();
            while (mapIter.hasNext()) {
                String skillName = mapIter.next();
                Integer num = map.get(skillName);
                if (tempHolder.containsKey(num)) {
                    tempHolder.get(num).add(skillName);
                }
                else {
                    ArrayList<String> tempList = new ArrayList<>();
                    tempList.add(skillName);
                    tempHolder.put(num, tempList);
                }
            }
            
            
            
            ArrayList<Custom> otherSkills = new ArrayList<>();
            Iterator<Integer> sortingIter = tempHolder.descendingKeySet().iterator();
            while (sortingIter.hasNext()) {
                Integer num = sortingIter.next();
                ArrayList<String> skills = tempHolder.get(num);
                Collections.sort(skills);
                if (num > 1) {
                    for (String skillName : skills) {
                        otherSkills.add(new Custom(true, skillName));
                    }
                }
                else {
                    for (String skillName : skills) {
                        otherSkills.add(new Custom(false, skillName));
                    }
                }
            }
            
            
            
            ArrayList<ArrayList<Custom>> unachievedList = new ArrayList<>();
            int totalCount = 0;
            count = 0;
            ArrayList<Custom> temp1 = null;
            for (int i = 0; i < otherSkills.size(); i++) {
                if (count == 4) {
                    unachievedList.add(temp1);
                    count = 0;
                }
                if (count == 0) {
                    temp1 = new ArrayList<>();
                }
                temp1.add(otherSkills.get(i));
                count++;
                totalCount++;
                if (totalCount == 16) {
                    break;
                }
            }
            if (temp != null && !temp.isEmpty() && !unachievedList.contains(temp)) {
                unachievedList.add(temp1);
            }
            
            
            
            flash.put("courses", courseList);
            flash.put("no", courseList.size());
            flash.put("track", track);
            flash.put("unachievedList", unachievedList);
            flash.put("achievedList", achievedList);
            FacesContext.getCurrentInstance().getExternalContext().redirect("TrackSearchResults.jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void hasSearchItem() {
        try{
            Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
            if (flash.get("courses") == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("userhome.jsf");
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
