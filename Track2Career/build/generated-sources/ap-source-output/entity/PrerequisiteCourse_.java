package entity;

import entity.Course;
import entity.PrerequisiteCoursePK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-04T19:02:58")
@StaticMetamodel(PrerequisiteCourse.class)
public class PrerequisiteCourse_ { 

    public static volatile SingularAttribute<PrerequisiteCourse, PrerequisiteCoursePK> prerequisiteCoursePK;
    public static volatile SingularAttribute<PrerequisiteCourse, Course> course;

}