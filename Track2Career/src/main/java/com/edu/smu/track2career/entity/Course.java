/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.smu.track2career.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c")
    , @NamedQuery(name = "Course.findByCourseId", query = "SELECT c FROM Course c WHERE c.courseId = :courseId")
    , @NamedQuery(name = "Course.findByCourseName", query = "SELECT c FROM Course c WHERE c.courseName = :courseName")
    , @NamedQuery(name = "Course.findByInstructorName", query = "SELECT c FROM Course c WHERE c.instructorName = :instructorName")})
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "course_id")
    private String courseId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "course_name")
    private String courseName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "instructor_name")
    private String instructorName;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Collection<UserCourse> userCourseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Collection<Software> softwareCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Collection<Skill> skillCollection;
    @JoinColumn(name = "track_id", referencedColumnName = "track_id")
    @ManyToOne(optional = false)
    private Track trackId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Collection<PrerequisiteCourse> prerequisiteCourseCollection;

    public Course() {
    }

    public Course(String courseId) {
        this.courseId = courseId;
    }

    public Course(String courseId, String courseName, String instructorName, String description) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructorName = instructorName;
        this.description = description;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<UserCourse> getUserCourseCollection() {
        return userCourseCollection;
    }

    public void setUserCourseCollection(Collection<UserCourse> userCourseCollection) {
        this.userCourseCollection = userCourseCollection;
    }

    @XmlTransient
    public Collection<Software> getSoftwareCollection() {
        return softwareCollection;
    }

    public void setSoftwareCollection(Collection<Software> softwareCollection) {
        this.softwareCollection = softwareCollection;
    }

    @XmlTransient
    public Collection<Skill> getSkillCollection() {
        return skillCollection;
    }

    public void setSkillCollection(Collection<Skill> skillCollection) {
        this.skillCollection = skillCollection;
    }

    public Track getTrackId() {
        return trackId;
    }

    public void setTrackId(Track trackId) {
        this.trackId = trackId;
    }

    @XmlTransient
    public Collection<PrerequisiteCourse> getPrerequisiteCourseCollection() {
        return prerequisiteCourseCollection;
    }

    public void setPrerequisiteCourseCollection(Collection<PrerequisiteCourse> prerequisiteCourseCollection) {
        this.prerequisiteCourseCollection = prerequisiteCourseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseId != null ? courseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.courseId == null && other.courseId != null) || (this.courseId != null && !this.courseId.equals(other.courseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.edu.smu.track2career.entity.Course[ courseId=" + courseId + " ]";
    }
    
}
