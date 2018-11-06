package entity;

import entity.Course;
import entity.SoftwarePK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-05T15:19:33")
@StaticMetamodel(Software.class)
public class Software_ { 

    public static volatile SingularAttribute<Software, SoftwarePK> softwarePK;
    public static volatile SingularAttribute<Software, String> softwareName;
    public static volatile SingularAttribute<Software, Course> course;

}