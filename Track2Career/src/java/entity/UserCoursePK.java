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
public class UserCoursePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "user_course_id")
    private String userCourseId;
    @Basic(optional = false)
    @Column(name = "course_id")
    private String courseId;
    @Basic(optional = false)
    @Column(name = "user_id")
    private String userId;

    public UserCoursePK() {
    }

    public UserCoursePK(String userCourseId, String courseId, String userId) {
        this.userCourseId = userCourseId;
        this.courseId = courseId;
        this.userId = userId;
    }

    public String getUserCourseId() {
        return userCourseId;
    }

    public void setUserCourseId(String userCourseId) {
        this.userCourseId = userCourseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userCourseId != null ? userCourseId.hashCode() : 0);
        hash += (courseId != null ? courseId.hashCode() : 0);
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserCoursePK)) {
            return false;
        }
        UserCoursePK other = (UserCoursePK) object;
        if ((this.userCourseId == null && other.userCourseId != null) || (this.userCourseId != null && !this.userCourseId.equals(other.userCourseId))) {
            return false;
        }
        if ((this.courseId == null && other.courseId != null) || (this.courseId != null && !this.courseId.equals(other.courseId))) {
            return false;
        }
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserCoursePK[ userCourseId=" + userCourseId + ", courseId=" + courseId + ", userId=" + userId + " ]";
    }
    
}
