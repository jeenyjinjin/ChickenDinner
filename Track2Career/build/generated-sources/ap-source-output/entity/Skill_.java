package entity;

import entity.Course;
import entity.SkillPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-04T19:02:58")
@StaticMetamodel(Skill.class)
public class Skill_ { 

    public static volatile SingularAttribute<Skill, String> skillName;
    public static volatile SingularAttribute<Skill, SkillPK> skillPK;
    public static volatile SingularAttribute<Skill, Course> course;

}