/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "software")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Software.findAll", query = "SELECT s FROM Software s")
    , @NamedQuery(name = "Software.findBySoftwareId", query = "SELECT s FROM Software s WHERE s.softwarePK.softwareId = :softwareId")
    , @NamedQuery(name = "Software.findBySoftwareName", query = "SELECT s FROM Software s WHERE s.softwareName = :softwareName")
    , @NamedQuery(name = "Software.findByCourseId", query = "SELECT s FROM Software s WHERE s.softwarePK.courseId = :courseId")})
public class Software implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SoftwarePK softwarePK;
    @Basic(optional = false)
    @Column(name = "software_name")
    private String softwareName;
    @JoinColumn(name = "course_id", referencedColumnName = "course_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Course course;

    public Software() {
    }

    public Software(SoftwarePK softwarePK) {
        this.softwarePK = softwarePK;
    }

    public Software(SoftwarePK softwarePK, String softwareName) {
        this.softwarePK = softwarePK;
        this.softwareName = softwareName;
    }

    public Software(String softwareId, String courseId) {
        this.softwarePK = new SoftwarePK(softwareId, courseId);
    }

    public SoftwarePK getSoftwarePK() {
        return softwarePK;
    }

    public void setSoftwarePK(SoftwarePK softwarePK) {
        this.softwarePK = softwarePK;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
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
        hash += (softwarePK != null ? softwarePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Software)) {
            return false;
        }
        Software other = (Software) object;
        if ((this.softwarePK == null && other.softwarePK != null) || (this.softwarePK != null && !this.softwarePK.equals(other.softwarePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Software[ softwarePK=" + softwarePK + " ]";
    }
    
}
