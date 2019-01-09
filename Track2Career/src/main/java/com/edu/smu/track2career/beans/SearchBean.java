package com.edu.smu.track2career.beans;

import com.edu.smu.track2career.entity.*;
import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import com.edu.smu.track2career.manager.*;
import com.google.gson.*;
import java.io.IOException;
import java.util.*;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "search", eager = true)
@SessionScoped
public class SearchBean {

    public String job;
    private ArrayList<String> jobArr;
    private JsonArray fullData;
    private ArrayList<String> industryList;
    private LinkedHashMap<String, TreeSet<String>> jobData;

    public String messageJ;
    public String messageT;

    public String track;
    public List<Track> initialTracks;

    // search results storage
    public List<Course> courseList;
    public ArrayList<ArrayList<String>> achievedList;
    public ArrayList<ArrayList<Custom>> unachievedList;

    public SearchBean() {
        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        try {
            TypedQuery<Track> query = em.createQuery("SELECT t FROM Track t", Track.class);
            initialTracks = query.getResultList();

            String url = "https://jobsense.sg/api/get/js-2-valid-job-ind-list/";
            JsonObject results = RestfulManager.sendGet(url);

            fullData = new JsonArray();
            Set<String> jobList = new LinkedHashSet<>();

            JsonArray dataArr = results.get("data").getAsJsonArray();
            for (int i = 0; i < dataArr.size(); i++) {
                String job_industry_pair = dataArr.get(i).getAsString();
                String job = StringManager.convertToTitleCaseIteratingChars(job_industry_pair.split("_")[0]);
                String industry = job_industry_pair.split("_")[1];

                JsonObject obj = new JsonObject();
                obj.addProperty("job_title", job);
                obj.addProperty("industry_name", industry);
                fullData.add(obj);

                jobList.add(job);
            }
            ArrayList<String> temp = new ArrayList<>(jobList);
            Collections.sort(temp);

            jobArr = temp;

            industryList = new ArrayList<>();
            url = "https://jobsense.sg/api/get/js-2-valid-ind-list/";
            results = RestfulManager.sendGet(url);

            dataArr = results.get("data").getAsJsonArray();
            for (int i = 0; i < dataArr.size(); i++) {
                String industry = dataArr.get(i).getAsString();
                if (!industry.trim().isEmpty()) {
                    industry = industry.trim();
                    industryList.add(industry);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        em.close();
        track = "";
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public LinkedHashMap<String, TreeSet<String>> getJobData() {
        return jobData;
    }

    public void setJobData(LinkedHashMap<String, TreeSet<String>> jobData) {
        this.jobData = jobData;
    }

    public String getMessageJ() {
        return messageJ;
    }

    public void setMessageJ(String messageJ) {
        this.messageJ = messageJ;
    }

    public String getMessageT() {
        return messageT;
    }

    public void setMessageT(String messageT) {
        this.messageT = messageT;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public List<Track> getInitialTracks() {
        return initialTracks;
    }

    public void setInitialTracks(List<Track> initialTracks) {
        this.initialTracks = initialTracks;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public ArrayList<ArrayList<String>> getAchievedList() {
        return achievedList;
    }

    public void setAchievedList(ArrayList<ArrayList<String>> achievedList) {
        this.achievedList = achievedList;
    }

    public ArrayList<ArrayList<Custom>> getUnachievedList() {
        return unachievedList;
    }

    public void setUnachievedList(ArrayList<ArrayList<Custom>> unachievedList) {
        this.unachievedList = unachievedList;
    }

    public List<String> completeText(String query) {
        if (query.trim().length() > 0) {
            ArrayList<String> searchRes = new ArrayList<>();
            for (String str : jobArr) {
                if (str.matches("(.*)(?i)(" + query + ")(.*)")) {
                    if (str.toLowerCase().indexOf(query.toLowerCase()) == 0) {
                        searchRes.add(0, str);
                    } else {
                        searchRes.add(str);
                    }
                }
            }
            return searchRes;
        }
        List<String> empty = new ArrayList<>();
        return empty;
    }

    public <T, S> List<Map.Entry<T, S>> mapToList(Map<T, S> map) {
        if (map == null) {
            return null;
        }

        List<Map.Entry<T, S>> list = new ArrayList<>();
        list.addAll(map.entrySet());

        return list;
    }

    public void submitJob() {
        try {
            List<String> relatedJobs = completeText(job);
            jobData = new LinkedHashMap<>();

            for (String jobName : relatedJobs) {
                TreeSet<String> industries = new TreeSet<>();
                for (int i = 0; i < fullData.size(); i++) {
                    JsonObject obj = fullData.get(i).getAsJsonObject();
                    String jobTitle = obj.get("job_title").getAsString();
                    String industryName = obj.get("industry_name").getAsString();
                    industryName = industryName.replace(" is in the list", "");
                    industryName = industryName.trim();

                    if (jobTitle.equalsIgnoreCase(jobName)) {
                        int industryId = industryList.indexOf(industryName);
                        industries.add(industryName);

//                        String url = "https://jobsense.sg/api/get/job-skill-gind-for-g-job/";
//                        String urlParameters = "{ \"jobtitle\" : \"" + jobTitle.toLowerCase() + "\", \"industry_id\" : " + industryId + ", \"no_of_results\" : 10 }";
//                        urlParameters = urlParameters.replaceAll(" ", "%20");
//                        JsonObject skillsResults = RestfulManager.sendPost(url, urlParameters);
//                        System.out.println(skillsResults.toString());
                    }
                }
                jobData.put(jobName, industries);
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("JobList.jsf");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void submitTrack() {
        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        try {

            // Retrieve all courses in the specified track
            TypedQuery<Course> courseQuery = em.createQuery("SELECT c FROM Course c INNER JOIN c.trackId t WHERE t.trackName = ?1 AND c.trackId.trackId = t.trackId", Course.class);
            courseQuery.setParameter(1, track);

            courseList = courseQuery.getResultList();

            // Retrieve all skills for all courses in the specified track
            TypedQuery<String> skillQuery = em.createQuery("SELECT s.skillName FROM Course c INNER JOIN c.trackId t INNER JOIN c.skillCollection s WHERE t.trackName = ?1 AND c.trackId.trackId = t.trackId", String.class);
            skillQuery.setParameter(1, track);

            List<String> skillList = skillQuery.getResultList();

            // Count number of times each skills appeared
            TreeMap<String, Integer> map = new TreeMap<>();
            Iterator<String> skillIter = skillList.iterator();
            while (skillIter.hasNext()) {
                String skillName = skillIter.next();
                Integer num = 1;
                if (map.containsKey(skillName)) {
                    num = map.get(skillName) + 1;
                }
                map.put(skillName, num);
            }

            // Retrieve userbean session
            UserBean ub = (UserBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            String userId = ub.getUser().getUserId();

            // Retrieve all skills that the authenticated user has
            TypedQuery<String> userSkillsQuery = em.createQuery("SELECT s.skillName FROM UserCourse uc INNER JOIN uc.courseId c INNER JOIN c.skillCollection s WHERE uc.userId.userId = ?1", String.class);
            userSkillsQuery.setParameter(1, userId);

            List<String> userSkills = userSkillsQuery.getResultList();

            // Iterate through the user skills and remove those that user has from the map
            List<String> ownedSkills = new ArrayList<>();
            for (String skillName : userSkills) {
                if (map.containsKey(skillName)) {
                    ownedSkills.add(skillName);
                    map.remove(skillName);
                }
            }

            // Arrange the skills in each arraylist of 4 for achieved skills
            achievedList = new ArrayList<>();
            int count = 0;
            ArrayList<String> temp = null;
            for (int i = 0; i < ownedSkills.size(); i++) {
                if (count == 4) {
                    achievedList.add(temp);
                    count = 0;
                }
                if (count == 0) {
                    temp = new ArrayList<>();
                }
                temp.add(ownedSkills.get(i));
                count++;
            }

            if (temp != null && !temp.isEmpty()) {
                achievedList.add(temp);
            }

            // Arrange the rest of the elements in the map in accordance of count of appearance
            TreeMap<Integer, ArrayList<String>> tempHolder = new TreeMap<>();
            Iterator<String> mapIter = map.keySet().iterator();
            while (mapIter.hasNext()) {
                String skillName = mapIter.next();
                Integer num = map.get(skillName);
                if (tempHolder.containsKey(num)) {
                    tempHolder.get(num).add(skillName);
                } else {
                    ArrayList<String> tempList = new ArrayList<>();
                    tempList.add(skillName);
                    tempHolder.put(num, tempList);
                }
            }

            ArrayList<Custom> otherSkills = new ArrayList<>();
            Iterator<Integer> sortingIter = tempHolder.descendingKeySet().iterator();
            while (sortingIter.hasNext()) {
                Integer num = sortingIter.next();
                ArrayList<String> skills = tempHolder.get(num);
                Collections.sort(skills);
                if (num > 1) {
                    for (String skillName : skills) {
                        otherSkills.add(new Custom(true, skillName));
                    }
                } else {
                    for (String skillName : skills) {
                        otherSkills.add(new Custom(false, skillName));
                    }
                }
            }

            unachievedList = new ArrayList<>();
            int totalCount = 0;
            count = 0;
            ArrayList<Custom> temp1 = null;
            for (int i = 0; i < otherSkills.size(); i++) {
                if (count == 4) {
                    unachievedList.add(temp1);
                    count = 0;
                }
                if (count == 0) {
                    temp1 = new ArrayList<>();
                }
                temp1.add(otherSkills.get(i));
                count++;
                totalCount++;
            }
            if (temp1 != null && !temp1.isEmpty()) {
                unachievedList.add(temp1);
            }

            FacesContext.getCurrentInstance().getExternalContext().redirect("TrackDetails.jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hasSearchItem() {
        try {
            if (courseList == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("userhome.jsf");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
