/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Admin
 */
@Embeddable
public class PrerequisiteCoursePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "prerequisite_course_id")
    private String prerequisiteCourseId;
    @Basic(optional = false)
    @Column(name = "course_id")
    private String courseId;

    public PrerequisiteCoursePK() {
    }

    public PrerequisiteCoursePK(String prerequisiteCourseId, String courseId) {
        this.prerequisiteCourseId = prerequisiteCourseId;
        this.courseId = courseId;
    }

    public String getPrerequisiteCourseId() {
        return prerequisiteCourseId;
    }

    public void setPrerequisiteCourseId(String prerequisiteCourseId) {
        this.prerequisiteCourseId = prerequisiteCourseId;
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
        hash += (prerequisiteCourseId != null ? prerequisiteCourseId.hashCode() : 0);
        hash += (courseId != null ? courseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrerequisiteCoursePK)) {
            return false;
        }
        PrerequisiteCoursePK other = (PrerequisiteCoursePK) object;
        if ((this.prerequisiteCourseId == null && other.prerequisiteCourseId != null) || (this.prerequisiteCourseId != null && !this.prerequisiteCourseId.equals(other.prerequisiteCourseId))) {
            return false;
        }
        if ((this.courseId == null && other.courseId != null) || (this.courseId != null && !this.courseId.equals(other.courseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PrerequisiteCoursePK[ prerequisiteCourseId=" + prerequisiteCourseId + ", courseId=" + courseId + " ]";
    }
    
}
