package com.edu.smu.track2career.entity;

import com.edu.smu.track2career.entity.Track;
import com.edu.smu.track2career.entity.UserCourse;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-09T20:48:07")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> school;
    public static volatile SingularAttribute<User, Track> trackId;
    public static volatile SingularAttribute<User, String> userType;
    public static volatile SingularAttribute<User, String> userId;
    public static volatile CollectionAttribute<User, UserCourse> userCourseCollection;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}