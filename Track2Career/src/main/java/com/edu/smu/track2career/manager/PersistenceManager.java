package com.edu.smu.track2career.manager;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class PersistenceManager {
    
    public static EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("com.edu.smu.track2career_Track2Career_war_1.0-SNAPSHOTPU").createEntityManager();
    }
    
}