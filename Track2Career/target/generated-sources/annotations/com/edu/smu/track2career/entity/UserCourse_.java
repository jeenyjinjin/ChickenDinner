package com.edu.smu.track2career.entity;

import com.edu.smu.track2career.entity.Course;
import com.edu.smu.track2career.entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-15T12:08:22")
@StaticMetamodel(UserCourse.class)
public class UserCourse_ { 

    public static volatile SingularAttribute<UserCourse, Integer> userCourseId;
    public static volatile SingularAttribute<UserCourse, User> userId;
    public static volatile SingularAttribute<UserCourse, Course> courseId;

}