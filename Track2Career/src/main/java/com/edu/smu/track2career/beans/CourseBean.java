package com.edu.smu.track2career.beans;

import com.edu.smu.track2career.entity.Skill;
import com.edu.smu.track2career.entity.UserCourse;
import com.edu.smu.track2career.manager.PersistenceManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@ManagedBean(name = "course", eager = true)
@RequestScoped
public class CourseBean {

    @ManagedProperty(value = "#{param.id}")
    private String id;

    @ManagedProperty(value = "#{param.name}")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void viewMore() {
        UserBean ub = (UserBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        String userId = ub.getUser().getUserId();

        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        try {
            Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

            TypedQuery<Skill> skillQuery = em.createQuery("SELECT s FROM Skill s WHERE s.course.courseId = ?1", Skill.class);
            skillQuery.setParameter(1, id);

            List<Skill> skills = skillQuery.getResultList();

            TypedQuery<String> userSkillsQuery = em.createQuery("SELECT s.skillName FROM UserCourse uc INNER JOIN uc.courseId c INNER JOIN c.skillCollection s WHERE uc.userId.userId = ?1", String.class);
            userSkillsQuery.setParameter(1, userId);

            List<String> userSkills = userSkillsQuery.getResultList();
            List<Skill> ownedSkills = new ArrayList<>();
            Iterator<Skill> iterator = skills.iterator();

            while (iterator.hasNext()) {
                Skill skill = iterator.next();
                String skillName = skill.getSkillName();
                if (userSkills.contains(skillName)) {
                    ownedSkills.add(skill);
                    iterator.remove();
                }
            }

            if (ownedSkills.isEmpty()) {
                Skill s = new Skill();
                s.setSkillName("NIL");
                ownedSkills.add(s);
            }
            if (skills.isEmpty()) {
                Skill s = new Skill();
                s.setSkillName("NIL");
                skills.add(s);
            }
            
            flash.put("ownedSkills", ownedSkills);
            flash.put("skills", skills);
            FacesContext.getCurrentInstance().getExternalContext().redirect("TrackSpecificSkills.jsf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
