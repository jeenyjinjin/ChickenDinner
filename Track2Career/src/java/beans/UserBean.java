package beans;

import entity.User;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import manager.PersistenceManager;

@ManagedBean(name = "user", eager = true)
@SessionScoped
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;
    public String username;
    public String password;
    public String message;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void submit() throws IOException {
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
//            return "login.jsf";
            return;
        }

        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2", User.class);
            query.setParameter(1, username);
            query.setParameter(2, password);

            User user = query.getSingleResult();
            if (user != null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("userhome.jsf");
            }
            
        } catch (Exception e) {
            message = "Invalid credentials detected!";
        }
    }
}
