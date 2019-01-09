package com.edu.smu.track2career.beans;

import com.edu.smu.track2career.entity.*;
import java.io.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.persistence.*;
import com.edu.smu.track2career.manager.*;
import java.util.*;
import javax.faces.event.AjaxBehaviorEvent;

@ManagedBean(name = "user", eager = true)
@SessionScoped
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;
    public String username;
    public String password;
    public String message;
    public User user;

    public String fullName;
    public String primaryOccupation;
    public String secondaryOccupation;
    public List<String> selectedCourses;
    public List<String> selectedSkills;
    public String phone;
    public String email;
    public String work;
    public String profileMessage;

    public List<Course> courses;
    public LinkedHashSet<String> skills;

    public UserBean() {
        fullName = "Regan Seah";
        primaryOccupation = "Student";
        secondaryOccupation = "";
        skills = new LinkedHashSet<>();
        phone = "9358 5528";
        email = "reganseah.2015@sis.smu.edu.sg";
        work = "";

        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        try {
            TypedQuery<Course> courseQuery = em.createQuery("SELECT c FROM Course c", Course.class);
            courses = courseQuery.getResultList();

            TypedQuery<Skill> skillQuery = em.createQuery("SELECT s FROM Skill s", Skill.class);
            List<Skill> skillsRes = skillQuery.getResultList();

            for (Skill skill : skillsRes) {
                skills.add(skill.getSkillName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        em.close();

        selectedCourses = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPrimaryOccupation() {
        return primaryOccupation;
    }

    public void setPrimaryOccupation(String primaryOccupation) {
        this.primaryOccupation = primaryOccupation;
    }

    public String getSecondaryOccupation() {
        return secondaryOccupation;
    }

    public void setSecondaryOccupation(String secondaryOccupation) {
        this.secondaryOccupation = secondaryOccupation;
    }

    public List<String> getSelectedCourses() {
        return selectedCourses;
    }

    public void setSelectedCourses(List<String> selectedCourses) {
        this.selectedCourses = selectedCourses;
    }

    public List<String> getSelectedSkills() {
        return selectedSkills;
    }

    public void setSelectedSkills(List<String> selectedSkills) {
        this.selectedSkills = selectedSkills;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getProfileMessage() {
        return profileMessage;
    }

    public void setProfileMessage(String profileMessage) {
        this.profileMessage = profileMessage;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(LinkedHashSet<String> skills) {
        this.skills = skills;
    }

    public void checkLoggedIn() {
        try {
            if (user == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void selectCourse(AjaxBehaviorEvent e) {
        Set<String> skillList = new TreeSet<>();
        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        for (String course : selectedCourses) {
            TypedQuery<String> query = em.createQuery("Select s.skillName FROM Skill s WHERE s.course.courseId = ?1", String.class);
            query.setParameter(1, course);

            List<String> skillResults = query.getResultList();
            skillList.addAll(skillResults);
        }
        em.close();
        selectedSkills = new ArrayList<>(skillList);
    }

    public void updateDetails() {
        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        try {
            TypedQuery<UserCourse> query = em.createQuery("Select uc FROM UserCourse uc WHERE uc.userId.userId = ?1", UserCourse.class);
            query.setParameter(1, user.getUserId());

            List<UserCourse> results = query.getResultList();
            if (results.isEmpty()) {
                for (String courseId : selectedCourses) {
                    UserCourse uc = new UserCourse();

                    TypedQuery<Course> courseQuery = em.createQuery("Select c FROM Course c WHERE c.courseId = ?1", Course.class);
                    courseQuery.setParameter(1, courseId);
                    Course course = courseQuery.getSingleResult();

                    uc.setCourseId(course);
                    uc.setUserId(user);

                    em.persist(uc);
                }
                em.getTransaction().commit();
                em.close();
            } else {
                List<String> omitList = new ArrayList<>();
                for (UserCourse uc : results) {
                    String courseId = uc.getCourseId().getCourseId();
                    if (!selectedCourses.contains(courseId)) {
                        em.remove(uc);
                    } else {
                        omitList.add(courseId);
                    }
                }

                for (String courseId : selectedCourses) {
                    if (!omitList.contains(courseId)) {
                        UserCourse uc = new UserCourse();

                        TypedQuery<Course> courseQuery = em.createQuery("Select c FROM Course c WHERE c.courseId = ?1", Course.class);
                        courseQuery.setParameter(1, courseId);
                        Course course = courseQuery.getSingleResult();

                        uc.setCourseId(course);
                        uc.setUserId(user);

                        em.persist(uc);
                    }
                }
                em.getTransaction().commit();
                em.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void submit() throws IOException {
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            return;
        }

        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2", User.class);
            query.setParameter(1, username);
            query.setParameter(2, password);

            user = query.getSingleResult();
            if (user != null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("userhome.jsf");
            }

        } catch (Exception e) {
            message = "Invalid credentials detected!";
        }
    }

    public void checkMessage() {
        if (message != null && !message.isEmpty()) {
            message = "";
        }
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }
}
