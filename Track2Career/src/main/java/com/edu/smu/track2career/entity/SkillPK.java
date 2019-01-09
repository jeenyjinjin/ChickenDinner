/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.smu.track2career.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Embeddable
public class SkillPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "skill_id")
    private String skillId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "course_id")
    private String courseId;

    public SkillPK() {
    }

    public SkillPK(String skillId, String courseId) {
        this.skillId = skillId;
        this.courseId = courseId;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (skillId != null ? skillId.hashCode() : 0);
        hash += (courseId != null ? courseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkillPK)) {
            return false;
        }
        SkillPK other = (SkillPK) object;
        if ((this.skillId == null && other.skillId != null) || (this.skillId != null && !this.skillId.equals(other.skillId))) {
            return false;
        }
        if ((this.courseId == null && other.courseId != null) || (this.courseId != null && !this.courseId.equals(other.courseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.edu.smu.track2career.entity.SkillPK[ skillId=" + skillId + ", courseId=" + courseId + " ]";
    }
    
}
