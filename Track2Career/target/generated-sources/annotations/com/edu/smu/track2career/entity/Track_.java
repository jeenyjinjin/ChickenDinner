package com.edu.smu.track2career.entity;

import com.edu.smu.track2career.entity.Course;
import com.edu.smu.track2career.entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-09T20:48:07")
@StaticMetamodel(Track.class)
public class Track_ { 

    public static volatile SingularAttribute<Track, String> trackId;
    public static volatile CollectionAttribute<Track, User> userCollection;
    public static volatile CollectionAttribute<Track, Course> courseCollection;
    public static volatile SingularAttribute<Track, String> trackName;

}