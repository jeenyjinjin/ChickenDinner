/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.smu.track2career.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "skill")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Skill.findAll", query = "SELECT s FROM Skill s")
    , @NamedQuery(name = "Skill.findBySkillId", query = "SELECT s FROM Skill s WHERE s.skillPK.skillId = :skillId")
    , @NamedQuery(name = "Skill.findBySkillName", query = "SELECT s FROM Skill s WHERE s.skillName = :skillName")
    , @NamedQuery(name = "Skill.findByCourseId", query = "SELECT s FROM Skill s WHERE s.skillPK.courseId = :courseId")})
public class Skill implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SkillPK skillPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "skill_name")
    private String skillName;
    @JoinColumn(name = "course_id", referencedColumnName = "course_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Course course;

    public Skill() {
    }

    public Skill(SkillPK skillPK) {
        this.skillPK = skillPK;
    }

    public Skill(SkillPK skillPK, String skillName) {
        this.skillPK = skillPK;
        this.skillName = skillName;
    }

    public Skill(String skillId, String courseId) {
        this.skillPK = new SkillPK(skillId, courseId);
    }

    public SkillPK getSkillPK() {
        return skillPK;
    }

    public void setSkillPK(SkillPK skillPK) {
        this.skillPK = skillPK;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (skillPK != null ? skillPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Skill)) {
            return false;
        }
        Skill other = (Skill) object;
        if ((this.skillPK == null && other.skillPK != null) || (this.skillPK != null && !this.skillPK.equals(other.skillPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.edu.smu.track2career.entity.Skill[ skillPK=" + skillPK + " ]";
    }
    
}
