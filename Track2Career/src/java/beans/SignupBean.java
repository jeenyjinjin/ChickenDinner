package beans;

import entity.User;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import manager.PersistenceManager;

@ManagedBean(name = "signup", eager = true)
@RequestScoped
public class SignupBean implements Serializable {

    private static final long serialVersionUID = 1L;
    public String nric;
    public String username;
    public String password;
    public String email;
    public List<String> schools;
    public String school;
    public List<String> skills;
    public String skill;
    public String message;

    public SignupBean() {
        schools = Arrays.asList("SMU", "Other Universities", "JC", "Polytechnic", "International School");
        skills = Arrays.asList("Python", "Consulting", "Management", "Java", "CPA", "Leadership", "Socialogical frameworks", "C++");
        school = "";
    }

    public String getNric() {
        return nric;
    }

    public void setNric(String nric) {
        this.nric = nric;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getSchools() {
        return schools;
    }

    public void setSchools(List<String> schools) {
        this.schools = schools;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String submit() {
        // do work
        // Check if the fields are filled up
        if (nric == null || nric.trim().isEmpty()) {
            message = "NRIC/FIN No. is required to be filled up!";
            return "signup";
        }
        if (username == null || username.trim().isEmpty()) {
            message = "Username is required to be filled up!";
            return "signup";
        }
        if (password == null || password.trim().isEmpty()) {
            message = "Password is required to be filled up!";
            return "signup";
        }
        if (email == null || email.trim().isEmpty()) {
            message = "Email is required to be filled up!";
            return "signup";
        }
        if (school == null || school.trim().isEmpty()) {
            message = "school is required to be chosen!";
            return "signup";
        }
        
        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        try {
            // Create User object
            User user = new User();
            user.setUserId(nric);
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setSchool(school);
            
            em.persist(user);
            em.getTransaction().commit();
            message = "Signed up successfully! You may log in now.";
            return "login";
            
        } catch (Exception ex) {
            String exMessage = ex.getMessage();
            if (exMessage.contains("Duplicate entry")) {
                message = "This NRIC/FIN is already registered!";
            }
            else {
                message = ex.getMessage();
            }
            return "signup";
        }
    }

}
