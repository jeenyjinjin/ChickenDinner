package entity;

import entity.Course;
import entity.User;
import entity.UserCoursePK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-04T19:02:58")
@StaticMetamodel(UserCourse.class)
public class UserCourse_ { 

    public static volatile SingularAttribute<UserCourse, UserCoursePK> userCoursePK;
    public static volatile SingularAttribute<UserCourse, Course> course;
    public static volatile SingularAttribute<UserCourse, User> user;

}