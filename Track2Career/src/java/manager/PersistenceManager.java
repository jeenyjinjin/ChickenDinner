package manager;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class PersistenceManager {
    
    public static EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("Track2CareerPU").createEntityManager();
    }
    
}