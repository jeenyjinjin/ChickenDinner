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
public class SoftwarePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "software_id")
    private String softwareId;
    @Basic(optional = false)
    @Column(name = "course_id")
    private String courseId;

    public SoftwarePK() {
    }

    public SoftwarePK(String softwareId, String courseId) {
        this.softwareId = softwareId;
        this.courseId = courseId;
    }

    public String getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(String softwareId) {
        this.softwareId = softwareId;
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
        hash += (softwareId != null ? softwareId.hashCode() : 0);
        hash += (courseId != null ? courseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SoftwarePK)) {
            return false;
        }
        SoftwarePK other = (SoftwarePK) object;
        if ((this.softwareId == null && other.softwareId != null) || (this.softwareId != null && !this.softwareId.equals(other.softwareId))) {
            return false;
        }
        if ((this.courseId == null && other.courseId != null) || (this.courseId != null && !this.courseId.equals(other.courseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SoftwarePK[ softwareId=" + softwareId + ", courseId=" + courseId + " ]";
    }
    
}
