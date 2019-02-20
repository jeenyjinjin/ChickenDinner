package com.edu.smu.track2career.entity.special;

import java.util.List;

public class JobData {
    private List<String> skills;
    private String jobtitle;
    private String industry;

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
    
    public String toString() {
        return "jobtitle: " + jobtitle + " , industry: " + industry;
    }
}
