package com.edu.smu.track2career.beans;

import com.edu.smu.track2career.entity.User;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import com.edu.smu.track2career.manager.PersistenceManager;

@ManagedBean(name = "signup", eager = true)
@RequestScoped
public class SignupBean implements Serializable {

    private static final long serialVersionUID = 1L;
    public String nric;
    public String type;
    public String username;
    public String password;
    public String email;
    public List<String> schools;
    public String school;
    public List<String> skills;
    public List<String> userTypes;
    public String skill;
    public String message;

    public SignupBean() {
        schools = Arrays.asList("SMU", "Other Universities", "JC", "Polytechnic", "International School");
        skills = Arrays.asList("Python", "Consulting", "Management", "Java", "CPA", "Leadership", "Socialogical frameworks", "C++");
        userTypes = Arrays.asList("Student", "Admin");
        school = "";
        type = "";
    }

    public String getNric() {
        return nric;
    }

    public void setNric(String nric) {
        this.nric = nric;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<String> getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(List<String> userTypes) {
        this.userTypes = userTypes;
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
        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        try {
            // Create User object
            User user = new User();
            user.setUserId(nric);
            user.setUserType(type);
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
    
    public void checkMessage() {
        if (message != null && !message.isEmpty()) {
            message = "";
        }
    }
}
