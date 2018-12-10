/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.smu.track2career.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "prerequisite_course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrerequisiteCourse.findAll", query = "SELECT p FROM PrerequisiteCourse p")
    , @NamedQuery(name = "PrerequisiteCourse.findByPrerequisiteCourseId", query = "SELECT p FROM PrerequisiteCourse p WHERE p.prerequisiteCoursePK.prerequisiteCourseId = :prerequisiteCourseId")
    , @NamedQuery(name = "PrerequisiteCourse.findByCourseId", query = "SELECT p FROM PrerequisiteCourse p WHERE p.prerequisiteCoursePK.courseId = :courseId")})
public class PrerequisiteCourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrerequisiteCoursePK prerequisiteCoursePK;
    @JoinColumn(name = "course_id", referencedColumnName = "course_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Course course;

    public PrerequisiteCourse() {
    }

    public PrerequisiteCourse(PrerequisiteCoursePK prerequisiteCoursePK) {
        this.prerequisiteCoursePK = prerequisiteCoursePK;
    }

    public PrerequisiteCourse(String prerequisiteCourseId, String courseId) {
        this.prerequisiteCoursePK = new PrerequisiteCoursePK(prerequisiteCourseId, courseId);
    }

    public PrerequisiteCoursePK getPrerequisiteCoursePK() {
        return prerequisiteCoursePK;
    }

    public void setPrerequisiteCoursePK(PrerequisiteCoursePK prerequisiteCoursePK) {
        this.prerequisiteCoursePK = prerequisiteCoursePK;
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
        hash += (prerequisiteCoursePK != null ? prerequisiteCoursePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrerequisiteCourse)) {
            return false;
        }
        PrerequisiteCourse other = (PrerequisiteCourse) object;
        if ((this.prerequisiteCoursePK == null && other.prerequisiteCoursePK != null) || (this.prerequisiteCoursePK != null && !this.prerequisiteCoursePK.equals(other.prerequisiteCoursePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.edu.smu.track2career.entity.PrerequisiteCourse[ prerequisiteCoursePK=" + prerequisiteCoursePK + " ]";
    }
    
}
