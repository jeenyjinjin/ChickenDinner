package com.edu.smu.track2career.entity;

import com.edu.smu.track2career.entity.Course;
import com.edu.smu.track2career.entity.SoftwarePK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-09T20:48:07")
@StaticMetamodel(Software.class)
public class Software_ { 

    public static volatile SingularAttribute<Software, SoftwarePK> softwarePK;
    public static volatile SingularAttribute<Software, String> softwareName;
    public static volatile SingularAttribute<Software, Course> course;

}