/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.smu.track2career.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "user_course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserCourse.findAll", query = "SELECT u FROM UserCourse u")
    , @NamedQuery(name = "UserCourse.findByUserCourseId", query = "SELECT u FROM UserCourse u WHERE u.userCourseId = :userCourseId")})
public class UserCourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_course_id")
    private Integer userCourseId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userId;
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    @ManyToOne(optional = false)
    private Course courseId;

    public UserCourse() {
    }

    public UserCourse(Integer userCourseId) {
        this.userCourseId = userCourseId;
    }

    public Integer getUserCourseId() {
        return userCourseId;
    }

    public void setUserCourseId(Integer userCourseId) {
        this.userCourseId = userCourseId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userCourseId != null ? userCourseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserCourse)) {
            return false;
        }
        UserCourse other = (UserCourse) object;
        if ((this.userCourseId == null && other.userCourseId != null) || (this.userCourseId != null && !this.userCourseId.equals(other.userCourseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.edu.smu.track2career.entity.UserCourse[ userCourseId=" + userCourseId + " ]";
    }
    
}
