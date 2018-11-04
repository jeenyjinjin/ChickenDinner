/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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
@Table(name = "user_course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserCourse.findAll", query = "SELECT u FROM UserCourse u")
    , @NamedQuery(name = "UserCourse.findByUserCourseId", query = "SELECT u FROM UserCourse u WHERE u.userCoursePK.userCourseId = :userCourseId")
    , @NamedQuery(name = "UserCourse.findByCourseId", query = "SELECT u FROM UserCourse u WHERE u.userCoursePK.courseId = :courseId")
    , @NamedQuery(name = "UserCourse.findByUserId", query = "SELECT u FROM UserCourse u WHERE u.userCoursePK.userId = :userId")})
public class UserCourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserCoursePK userCoursePK;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "course_id", referencedColumnName = "course_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Course course;

    public UserCourse() {
    }

    public UserCourse(UserCoursePK userCoursePK) {
        this.userCoursePK = userCoursePK;
    }

    public UserCourse(String userCourseId, String courseId, String userId) {
        this.userCoursePK = new UserCoursePK(userCourseId, courseId, userId);
    }

    public UserCoursePK getUserCoursePK() {
        return userCoursePK;
    }

    public void setUserCoursePK(UserCoursePK userCoursePK) {
        this.userCoursePK = userCoursePK;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        hash += (userCoursePK != null ? userCoursePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserCourse)) {
            return false;
        }
        UserCourse other = (UserCourse) object;
        if ((this.userCoursePK == null && other.userCoursePK != null) || (this.userCoursePK != null && !this.userCoursePK.equals(other.userCoursePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserCourse[ userCoursePK=" + userCoursePK + " ]";
    }
    
}
