package com.edu.smu.track2career.beans;

import com.edu.smu.track2career.entity.*;
import java.io.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.persistence.*;
import com.edu.smu.track2career.manager.*;
import com.google.gson.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.faces.event.AjaxBehaviorEvent;

@ManagedBean(name = "user", eager = true)
@SessionScoped
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;
    public String username;
    public String password;
    public String message;
    public User user;

    public String fullName;
    public String primaryOccupation;
    public String secondaryOccupation;
    public List<String> selectedCourses;
    public List<String> selectedSkills;
    public String phone;
    public String email;
    public String work;
    public String profileMessage;

    public List<Course> courses;
    public LinkedHashSet<String> skills;

    //for admin page, it will run when the user login as type admin
    public Integer totalUserCount;
    public Integer newUserCount;
    public Integer totalSearches;
    public String mostPopularTrack;
    public JsonObject trackSearchTraffic;
    public JsonObject jobSearchTraffic;
    public JsonObject trafficBreackDownByTrack;
    public List<List<String>> popularJobsRanking;
    public JsonObject schoolDistribution;
    //for user skill page, it will show the 
    public JsonArray overallTrackCompletionStatus;
    public Integer totalUserSkills;
    
    public UserBean() {
        fullName = "Regan Seah";
        primaryOccupation = "Student";
        secondaryOccupation = "";
        skills = new LinkedHashSet<>();
        phone = "9358 5528";
        email = "reganseah.2015@sis.smu.edu.sg";
        work = "";

        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        try {
            TypedQuery<Course> courseQuery = em.createQuery("SELECT c FROM Course c", Course.class);
            courses = courseQuery.getResultList();

            TypedQuery<Skill> skillQuery = em.createQuery("SELECT s FROM Skill s", Skill.class);
            List<Skill> skillsRes = skillQuery.getResultList();

            for (Skill skill : skillsRes) {
                skills.add(skill.getSkillName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        em.close();

        selectedCourses = new ArrayList<>();
        //need to improve here, as currently the selected courses never got stored into the database
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPrimaryOccupation() {
        return primaryOccupation;
    }

    public void setPrimaryOccupation(String primaryOccupation) {
        this.primaryOccupation = primaryOccupation;
    }

    public String getSecondaryOccupation() {
        return secondaryOccupation;
    }

    public void setSecondaryOccupation(String secondaryOccupation) {
        this.secondaryOccupation = secondaryOccupation;
    }

    public List<String> getSelectedCourses() {
        return selectedCourses;
    }

    public void setSelectedCourses(List<String> selectedCourses) {
        this.selectedCourses = selectedCourses;
    }

    public List<String> getSelectedSkills() {
        return selectedSkills;
    }

    public void setSelectedSkills(List<String> selectedSkills) {
        this.selectedSkills = selectedSkills;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getProfileMessage() {
        return profileMessage;
    }

    public void setProfileMessage(String profileMessage) {
        this.profileMessage = profileMessage;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(LinkedHashSet<String> skills) {
        this.skills = skills;
    }

    public Integer getTotalUserCount() {
        return totalUserCount;
    }

    public void setTotalUserCount(Integer totalUserCount) {
        this.totalUserCount = totalUserCount;
    }

    public Integer getNewUserCount() {
        return newUserCount;
    }

    public void setNewUserCount(Integer newUserCount) {
        this.newUserCount = newUserCount;
    }

    public Integer getTotalSearches() {
        return totalSearches;
    }

    public void setTotalSearches(Integer totalSearches) {
        this.totalSearches = totalSearches;
    }

    public String getMostPopularTrack() {
        return mostPopularTrack;
    }

    public void setMostPopularTrack(String mostPopularTrack) {
        this.mostPopularTrack = mostPopularTrack;
    }

    public JsonObject getTrackSearchTraffic() {
        return trackSearchTraffic;
    }

    public void setTrackSearchTraffic(JsonObject trackSearchTraffic) {
        this.trackSearchTraffic = trackSearchTraffic;
    }

    public JsonObject getJobSearchTraffic() {
        return jobSearchTraffic;
    }

    public void setJobSearchTraffic(JsonObject jobSearchTraffic) {
        this.jobSearchTraffic = jobSearchTraffic;
    }

    public JsonObject getTrafficBreackDownByTrack() {
        return trafficBreackDownByTrack;
    }

    public void setTrafficBreackDownByTrack(JsonObject trafficBreackDownByTrack) {
        this.trafficBreackDownByTrack = trafficBreackDownByTrack;
    }

    public List<List<String>> getPopularJobsRanking() {
        return popularJobsRanking;
    }

    public void setPopularJobsRanking(List<List<String>> popularJobsRanking) {
        this.popularJobsRanking = popularJobsRanking;
    }

    public JsonObject getSchoolDistribution() {
        return schoolDistribution;
    }

    public void setSchoolDistribution(JsonObject schoolDistribution) {
        this.schoolDistribution = schoolDistribution;
    }

    public JsonArray getOverallTrackCompletionStatus() {
        return overallTrackCompletionStatus;
    }

    public void setOverallTrackCompletionStatus(JsonArray overallTrackCompletionStatus) {
        this.overallTrackCompletionStatus = overallTrackCompletionStatus;
    }

    public Integer getTotalUserSkills() {
        return totalUserSkills;
    }

    public void setTotalUserSkills(Integer totalUserSkills) {
        this.totalUserSkills = totalUserSkills;
    }
    
    

    public boolean checkLoggedIn() {
        if (user == null) {
            return false;
        }
        return true;
//        try {
//            if (user == null) {
//                FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }

    public void selectCourse(AjaxBehaviorEvent e) {
        Set<String> skillList = new TreeSet<>();
        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        for (String course : selectedCourses) {
            TypedQuery<String> query = em.createQuery("Select s.skillName FROM Skill s WHERE s.course.courseId = ?1", String.class);
            query.setParameter(1, course);

            List<String> skillResults = query.getResultList();
            skillList.addAll(skillResults);
        }
        em.close();
        selectedSkills = new ArrayList<>(skillList);
    }

    public void updateDetails() {
        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        try {
            TypedQuery<UserCourse> query = em.createQuery("Select uc FROM UserCourse uc WHERE uc.userId.userId = ?1", UserCourse.class);
            query.setParameter(1, user.getUserId());

            List<UserCourse> results = query.getResultList();
            if (results.isEmpty()) {
                for (String courseId : selectedCourses) {
                    UserCourse uc = new UserCourse();

                    TypedQuery<Course> courseQuery = em.createQuery("Select c FROM Course c WHERE c.courseId = ?1", Course.class);
                    courseQuery.setParameter(1, courseId);
                    Course course = courseQuery.getSingleResult();

                    uc.setCourseId(course);
                    uc.setUserId(user);

                    em.persist(uc);
                }
                em.getTransaction().commit();
                em.close();
            } else {
                List<String> omitList = new ArrayList<>();
                for (UserCourse uc : results) {
                    String courseId = uc.getCourseId().getCourseId();
                    if (!selectedCourses.contains(courseId)) {
                        em.remove(uc);
                    } else {
                        omitList.add(courseId);
                    }
                }

                for (String courseId : selectedCourses) {
                    if (!omitList.contains(courseId)) {
                        UserCourse uc = new UserCourse();

                        TypedQuery<Course> courseQuery = em.createQuery("Select c FROM Course c WHERE c.courseId = ?1", Course.class);
                        courseQuery.setParameter(1, courseId);
                        Course course = courseQuery.getSingleResult();

                        uc.setCourseId(course);
                        uc.setUserId(user);

                        em.persist(uc);
                    }
                }
                em.getTransaction().commit();
                em.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void submit() throws IOException {
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            return;
        }

        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2", User.class);
            query.setParameter(1, username);
            query.setParameter(2, password);

            user = query.getSingleResult();
            if (user != null && user.getUserType().equals("Student")) {
                
                FacesContext.getCurrentInstance().getExternalContext().redirect("userhome.jsf");
            } else if (user != null && user.getUserType().equals("Admin")) {
                //start of retrieving a overall user stats, when the table gets larger, it will take more time
                //by that time we graduate already:)
                //so there is no need for a special cron job to save cpu power
                try {
                    TypedQuery<Long> getTotalUserQuery = em.createQuery("select count(u) from User u", Long.class);
                    totalUserCount = (getTotalUserQuery.getSingleResult()).intValue();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("data retrieval error");
                }
                try {
                    TypedQuery<Long> getTotalSearches = em.createQuery("select count(a) from AccessSummary a", Long.class);
                    totalSearches = (getTotalSearches.getSingleResult()).intValue();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("data retrieval error");
                }
                try {
                    TypedQuery<Object[]> getAllTrackSearchCount = em.createQuery("SELECT a.trackName, count(a) FROM AccessSummary AS a where a.accessType=?1 group by a.trackName", Object[].class);
                    getAllTrackSearchCount.setParameter(1, "T");
                    List<Object[]> allTrackSearchCount = getAllTrackSearchCount.getResultList();
                    String mostPopularTrackName = "";
                    int largestCount = 0;
                    JsonObject tempTrackHistoryBreakDown = new JsonObject();
                    for (int a = 0; a < allTrackSearchCount.size(); a++) {
                        Object[] currentTrackInfo = allTrackSearchCount.get(a);
                        if (currentTrackInfo instanceof Object[]) {
                            Object[] row = (Object[]) currentTrackInfo;
                            System.out.println("testing" + row[0] + row[1]);
                            String currentTrackName = String.valueOf(row[0]);

                            int currentTrackSearches = ((Long) row[1]).intValue();
                            tempTrackHistoryBreakDown.addProperty(currentTrackName, currentTrackSearches);
                            if (currentTrackSearches > largestCount) {
                                largestCount = currentTrackSearches;
                                mostPopularTrackName = currentTrackName;
                            }
                        }

                    }
                    mostPopularTrack = mostPopularTrackName;
                    trafficBreackDownByTrack = tempTrackHistoryBreakDown;
                    System.out.println("testing display: most popular track is " + mostPopularTrack);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("data retrieval error");
                }

                try {
                    TypedQuery<Object[]> getUserSchoolInfo = em.createQuery("SELECT u.school, count(u) from User u group by u.school", Object[].class);
                    List<Object[]> schoolBreakDown = getUserSchoolInfo.getResultList();
                    JsonObject tempSchoolDistribution = new JsonObject();

                    for (Object[] a : schoolBreakDown) {
                        String schoolName = String.valueOf(a[0]);
                        int count = ((Long) a[1]).intValue();
                        System.out.println("testSchool: " + schoolName + count);
                        tempSchoolDistribution.addProperty(schoolName, count);
                    }

                    schoolDistribution = tempSchoolDistribution;
                } catch (Exception e) {
                    System.out.println("data retrieval error");
                    e.printStackTrace();
                }

                try {
                    TypedQuery<Object[]> getTopJobs = em.createQuery("SELECT a.jobName, count(a) from AccessSummary a where a.accessType=?1 group by a.jobName having count(a)>1 order by count(a) desc", Object[].class);
                    getTopJobs.setParameter(1, "J");
                    List<Object[]> topJobs = getTopJobs.getResultList();
                    List<List<String>> tempPopularJobs = new ArrayList<>();
                    //get top 5 jobs of from the list
                    for (int a = 0; a < topJobs.size(); a++) {
                        if (a >= 5) {
                            break;
                        } else {
                            Object[] currentPair = topJobs.get(a);
                            String jobName = String.valueOf(currentPair[0]);
                            String count = String.valueOf(currentPair[1]);
                            System.out.println("testing topjobs : " + jobName + count);
                            List<String> tempJobArr = new ArrayList<>();
                            tempJobArr.add(jobName);
                            tempJobArr.add(count);
                            tempPopularJobs.add(tempJobArr);

                        }

                    }

                    popularJobsRanking = tempPopularJobs;

                } catch (Exception e) {
                    System.out.println("topJob query got issue");
                    e.printStackTrace();
                }

                try {
                    //use native sql string as jpql has poor support for converting dates
                    Query getWeeklyJobs = em.createNativeQuery("select date(access_time),count(*) from access_summary where access_type=?1 AND access_time> (curdate()-6) group by date(access_time)");
                    getWeeklyJobs.setParameter(1, "J");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar cal = Calendar.getInstance();
                    // get starting date
                    cal.add(Calendar.DAY_OF_YEAR, -6);
                    JsonObject tempJobDates = new JsonObject();
                    JsonObject tempTrackDates = new JsonObject();
                    // loop adding one day in each iteration
                    for (int i = 0; i < 6; i++) {
                        cal.add(Calendar.DAY_OF_YEAR, 1);
                        System.out.println(sdf.format(cal.getTime()));
                        tempJobDates.addProperty(sdf.format(cal.getTime()), 0);
                        tempTrackDates.addProperty(sdf.format(cal.getTime()), 0);
                    }
                    List<Object[]> weeklyJobs = getWeeklyJobs.getResultList();
                    for (Object[] a : weeklyJobs) {
                        //System.out.println("testing weeklyJobs: "+a[0]+a[1]);
                        tempJobDates.addProperty(String.valueOf(a[0]), ((Long) a[1]).intValue());

                    }

                    jobSearchTraffic = tempJobDates;

//                    TypedQuery<Object[]> getWeeklyTracks = em.createQuery("SELECT DATE(a.accessTime), count(a.accessTime) from AccessSummary a where a.accessType=?1 and a.accessTime>=(CURRENT_DATE-6) group by a.jobName",Object[].class);
                    Query getWeeklyTracks = em.createNativeQuery("select date(access_time),count(*) from access_summary where access_type=?1 AND access_time> (curdate()-6) group by date(access_time)");
                    getWeeklyTracks.setParameter(1, "T");
                    List<Object[]> weeklyTracks = getWeeklyTracks.getResultList();
                    for (Object[] a : weeklyTracks) {
                        System.out.println("testing weeklyTracks: " + a[0] + a[1]);
                        tempTrackDates.addProperty(String.valueOf(a[0]), ((Long) a[1]).intValue());

                    }
                    System.out.println(tempJobDates);
                    System.out.println(tempTrackDates);

                    trackSearchTraffic = tempTrackDates;

                } catch (Exception e) {
                    System.out.println("error in weekly jobs");
                    e.printStackTrace();
                }

                FacesContext.getCurrentInstance().getExternalContext().redirect("admin.jsf");

            }

        } catch (Exception e) {
            message = "Invalid credentials detected!";
        }
    }

    public void checkMessage() {
        if (message != null && !message.isEmpty()) {
            message = "";
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    
    //get all information required for the skill summary page
    public String retrieveSkillSummary() {
        JsonArray radarWrapper = new JsonArray();
        JsonArray tempTrackCompletionStatus = new JsonArray();
        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        try {
            // Retrieve userbean session
            UserBean ub = (UserBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            String userId = ub.getUser().getUserId();

            // Retrieve all skills that the authenticated user has
            TypedQuery<String> userSkillsQuery = em.createQuery("SELECT s.skillName FROM UserCourse uc INNER JOIN uc.courseId c INNER JOIN c.skillCollection s WHERE uc.userId.userId = ?1", String.class);
            userSkillsQuery.setParameter(1, userId);

            List<String> userSkills = userSkillsQuery.getResultList();
            totalUserSkills = userSkills.size();
            TypedQuery<Track> query = em.createQuery("SELECT t FROM Track t", Track.class);
            List<Track> allTracks = query.getResultList();
            for(Track currentTrack: allTracks){
                String trackName = currentTrack.getTrackName();
                String trackID = currentTrack.getTrackId();
                // Retrieve all skills for all courses in the specified track
                TypedQuery<String> skillQuery = em.createQuery("SELECT s.skillName FROM Course c INNER JOIN c.trackId t INNER JOIN c.skillCollection s WHERE t.trackName = ?1 AND c.trackId.trackId = t.trackId", String.class);
                skillQuery.setParameter(1, trackName);
                List<String> currentTrackSkills = skillQuery.getResultList();
                double currentTrackSkillCount=(double)currentTrackSkills.size();
                double possessedSkillsCount =(double)0;
                double trackProgress;
                //the common skills stores the common skills between user skills as track skills
                List<String> commonSkills = new ArrayList<String>(currentTrackSkills);
                //retain the skills that exists in both user skills and current track skills
                commonSkills.retainAll(userSkills);
                
                if(commonSkills!=null){
                    possessedSkillsCount=(double)commonSkills.size();
                }
                
                
                trackProgress = possessedSkillsCount/currentTrackSkillCount;
                
                
                
                JsonObject trackTemp = new JsonObject();
                trackTemp.addProperty("axis", trackName);
                trackTemp.addProperty("value", trackProgress);
                tempTrackCompletionStatus.add(trackTemp);
                
                
            }
            
            radarWrapper.add(tempTrackCompletionStatus);
            overallTrackCompletionStatus=radarWrapper;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //go to myskills page
        return "/mySkills.xhtml?faces-redirect=true";

    }

}
