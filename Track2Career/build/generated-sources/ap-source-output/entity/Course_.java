package entity;

import entity.PrerequisiteCourse;
import entity.Skill;
import entity.Software;
import entity.Track;
import entity.UserCourse;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-04T19:02:58")
@StaticMetamodel(Course.class)
public class Course_ { 

    public static volatile SingularAttribute<Course, String> courseName;
    public static volatile SingularAttribute<Course, String> instructorName;
    public static volatile CollectionAttribute<Course, Software> softwareCollection;
    public static volatile SingularAttribute<Course, Track> trackId;
    public static volatile CollectionAttribute<Course, Skill> skillCollection;
    public static volatile CollectionAttribute<Course, PrerequisiteCourse> prerequisiteCourseCollection;
    public static volatile SingularAttribute<Course, String> courseId;
    public static volatile CollectionAttribute<Course, UserCourse> userCourseCollection;

}