package com.edu.smu.track2career.entity.special;

import java.util.List;

public class SkillData {
    private String skill;
    private List<JobData> data;

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public List<JobData> getData() {
        return data;
    }

    public void setData(List<JobData> data) {
        this.data = data;
    }
}
