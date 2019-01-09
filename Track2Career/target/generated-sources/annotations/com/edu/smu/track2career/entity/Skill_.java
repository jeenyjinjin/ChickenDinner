package com.edu.smu.track2career.entity;

import com.edu.smu.track2career.entity.Course;
import com.edu.smu.track2career.entity.SkillPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-09T20:48:07")
@StaticMetamodel(Skill.class)
public class Skill_ { 

    public static volatile SingularAttribute<Skill, String> skillName;
    public static volatile SingularAttribute<Skill, SkillPK> skillPK;
    public static volatile SingularAttribute<Skill, Course> course;

}