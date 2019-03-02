package com.edu.smu.track2career.beans;

import com.edu.smu.track2career.entity.Course;
import com.edu.smu.track2career.entity.special.Custom;
import com.edu.smu.track2career.manager.PersistenceManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@ManagedBean(name = "course", eager = true)
@SessionScoped
public class CourseBean {

    private String courseId;
    private Course course;
    public ArrayList<ArrayList<String>> achievedList;
    public ArrayList<ArrayList<Custom>> unachievedList;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ArrayList<ArrayList<String>> getAchievedList() {
        return achievedList;
    }

    public void setAchievedList(ArrayList<ArrayList<String>> achievedList) {
        this.achievedList = achievedList;
    }

    public ArrayList<ArrayList<Custom>> getUnachievedList() {
        return unachievedList;
    }

    public void setUnachievedList(ArrayList<ArrayList<Custom>> unachievedList) {
        this.unachievedList = unachievedList;
    }

    //action listener event
    public void attrListener(ActionEvent event) {
        courseId = (String) event.getComponent().getAttributes().get("courseId");
    }

    public void viewMore() {
        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        try {         
            course = em.find(Course.class, courseId);

            TypedQuery<String> skillQuery = em.createQuery("SELECT s.skillName FROM Skill s WHERE s.course.courseId = ?1", String.class);
            skillQuery.setParameter(1, courseId);

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

            
            
            UserBean ub = (UserBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            String userId = ub.getUser().getUserId();
            
            
            
            TypedQuery<String> userSkillsQuery = em.createQuery("SELECT s.skillName FROM UserCourse uc INNER JOIN uc.courseId c INNER JOIN c.skillCollection s WHERE uc.userId.userId = ?1", String.class);
            userSkillsQuery.setParameter(1, userId);

            List<String> userSkills = userSkillsQuery.getResultList();
            
            
            List<String> ownedSkills = new ArrayList<>();
            for (String skillName : userSkills) {
                if (map.containsKey(skillName)) {
                    ownedSkills.add(skillName);
                    map.remove(skillName);
                }
            }
            
            
            // Arrange the skills in each arraylist of 4 for achieved skills
            achievedList = new ArrayList<>();
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
            
            if (temp != null && !temp.isEmpty()) {
                achievedList.add(temp);
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
            
            
            
            unachievedList = new ArrayList<>();
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
            }
            if (temp1 != null && !temp1.isEmpty()) {
                unachievedList.add(temp1);
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("CourseDetails.jsf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
