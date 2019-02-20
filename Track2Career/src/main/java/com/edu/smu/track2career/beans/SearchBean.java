package com.edu.smu.track2career.beans;

import com.edu.smu.track2career.entity.special.Custom;
import com.edu.smu.track2career.entity.*;
import com.edu.smu.track2career.entity.special.JobData;
import com.edu.smu.track2career.entity.special.SkillData;
import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import com.edu.smu.track2career.manager.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.text.similarity.JaccardSimilarity;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;


@ManagedBean(name = "search", eager = true)
@SessionScoped
public class SearchBean {

    private String job;
    private String industry;
    private int min;
    private int max;
    private int mean;
    private List<String> careerPath;

    private List<String> skillArr;

    private ArrayList<String> jobArr;
    private JsonArray fullData;
    private ArrayList<String> searchIndustryList;
    private ArrayList<String> industryList;
    private ArrayList<String> trackSkillList;
    private LinkedHashMap<String, TreeSet<String>> jobData;
    private List<JobData> skillDataList;

    private String messageJ;
    private String messageT;

    private String track;
    private List<Track> initialTracks;
    
    

    // search results storage
    private List<Course> courseList;
    private ArrayList<ArrayList<Custom>> achievedList;
    private ArrayList<ArrayList<Custom>> unachievedList;
    private ArrayList<Custom> topFifteenSkills;
    private JsonObject courseInTree;

    private JsonArray skillInBubble;

    public SearchBean() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String s = context.getRealPath("./data/");

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

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMean() {
        return mean;
    }

    public void setMean(int mean) {
        this.mean = mean;
    }

    public List<String> getCareerPath() {
        return careerPath;
    }

    public void setCareerPath(List<String> careerPath) {
        this.careerPath = careerPath;
    }

    public List<String> getSkillArr() {
        return skillArr;
    }

    public void setSkillArr(List<String> skillArr) {
        this.skillArr = skillArr;
    }

    public ArrayList<String> getSearchIndustryList() {
        return searchIndustryList;
    }

    public void setSearchIndustryList(ArrayList<String> searchIndustryList) {
        this.searchIndustryList = searchIndustryList;
    }

    public LinkedHashMap<String, TreeSet<String>> getJobData() {
        return jobData;
    }

    public void setJobData(LinkedHashMap<String, TreeSet<String>> jobData) {
        this.jobData = jobData;
    }

    public List<JobData> getSkillDataList() {
        return skillDataList;
    }

    public void setSkillDataList(List<JobData> skillDataList) {
        this.skillDataList = skillDataList;
    }

    public ArrayList<String> getTrackSkillList() {
        return trackSkillList;
    }

    public void setTrackSkillList(ArrayList<String> trackSkillList) {
        this.trackSkillList = trackSkillList;
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

    public ArrayList<ArrayList<Custom>> getAchievedList() {
        return achievedList;
    }

    public void setAchievedList(ArrayList<ArrayList<Custom>> achievedList) {
        this.achievedList = achievedList;
    }

    public ArrayList<ArrayList<Custom>> getUnachievedList() {
        return unachievedList;
    }

    public void setUnachievedList(ArrayList<ArrayList<Custom>> unachievedList) {
        this.unachievedList = unachievedList;
    }

    public ArrayList<Custom> getTopFifteenSkills() {
        return topFifteenSkills;
    }

    public void setTopFifteenSkills(ArrayList<Custom> topFifteenSkills) {
        this.topFifteenSkills = topFifteenSkills;
    }

    public JsonObject getCourseInTree() {
        return courseInTree;
    }

    public void setCourseInTree(JsonObject courseInTree) {
        this.courseInTree = courseInTree;
    }

    public void setSkillInBubble(JsonArray skillInBubble) {
        this.skillInBubble = skillInBubble;
    }

    public JsonArray getSkillInBubble() {
        return skillInBubble;
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

    public void onJobTitleSelected() {
        TreeSet<String> industries = new TreeSet<>();
        for (int i = 0; i < fullData.size(); i++) {
            JsonObject obj = fullData.get(i).getAsJsonObject();
            String jobTitle = obj.get("job_title").getAsString();
            String industryName = obj.get("industry_name").getAsString();
            industryName = industryName.replace(" is in the list", "");
            industryName = industryName.trim();

            if (jobTitle.equalsIgnoreCase(job)) {
                int industryId = industryList.indexOf(industryName);
                industries.add(industryName);
            }
        }

        if (!industries.isEmpty()) {
            searchIndustryList = new ArrayList<>(industries);
        } else {
            searchIndustryList = new ArrayList<>();
        }
    }

    public void onIndustrySelected() throws IOException {
        int industryId = industryList.indexOf(industry);
        //current issue: most times the insertion is okay, however, when the industry is onselected and there is wage, the data will be inserted twoce
        //good to solve this issue, it's okay to leaved it, cuz it's a very rare case
        //added entitymanager to insert user job search records to database
        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        UserBean ub = (UserBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        //insert job search data into database
        
        try{
            insertJobSearchToAccessSummary(em, ub.getUser().getSchool(),job);
            em.getTransaction().commit();
        }catch(Exception e){
            System.out.println("database insertion error");
            e.printStackTrace();
        }
        try {
            // Retrieve the skills for the specified job
            String url = "https://jobsense.sg/api/get/job-skill-gind-for-g-job/";
            String urlParameters = "{ \"jobtitle\" : \"" + job.toLowerCase() + "\", \"industry_id\" : " + industryId + ", \"no_of_results\" : 10 }";

            JsonObject skillsResults = RestfulManager.sendPost(url, urlParameters);
            JsonArray arr = skillsResults.get("data").getAsJsonArray();
            Type listType = new TypeToken<List<String>>() {
            }.getType();

            List<String> yourList = new Gson().fromJson(arr, listType);
            skillArr = yourList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        try {
            // Retrieve salary information for the specific job
            String url = "https://research.larc.smu.edu.sg/wagedb/api/get-salary-info/";
            String urlParameters = "jobtitle=" + job + "&industry=" + industry;
//            
            JsonObject results = RestfulManager.sendPostWageDb(url, urlParameters);
            if (!results.get("status").getAsBoolean()) {
                min = 0;
                mean = 0;
                max = 0;
            }
            else {
                JsonObject salaryInfo = results.get("salary-info").getAsJsonObject().get("salary-info").getAsJsonObject();

                min = salaryInfo.get("min-salary").getAsInt();
                mean = salaryInfo.get("mean-salary").getAsInt();
                max = salaryInfo.get("max-salary").getAsInt();
            }
//            System.out.println(results);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        try {
            careerPath = new ArrayList<>();
            
            // Retrieve the skills for the specified job
            String url = "https://jobsense.sg/api/get/js-2-pop-career-path/";
            String urlParameters = "{ \"jobtitle\" : \"" + job.toLowerCase() + "\", \"industry_id\" : " + industryId + "}";
            
            JsonObject careerPathResults = RestfulManager.sendPost(url, urlParameters);
            if (careerPathResults.get("status").getAsBoolean()) {
                JsonArray careerPathList1 = careerPathResults.get("data").getAsJsonArray();
                JsonArray careerPathList2 = careerPathList1.get(0).getAsJsonArray();
                for (JsonElement elem : careerPathList2) {
                    JsonObject career = elem.getAsJsonObject();
                    String jobtitle = career.get("jobtitle").getAsString();
                    careerPath.add(StringManager.convertToTitleCaseIteratingChars(jobtitle));
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("JobDetails.jsf");
    }

    public void submitJob() throws IOException {
        int industryId = industryList.indexOf(industry);
        //added entitymanager to insert user job search records to database
//        EntityManager em = PersistenceManager.getEntityManager();
//        em.getTransaction().begin();
//        UserBean ub = (UserBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
//        
//        try{
//            insertJobSearchToAccessSummary(em, ub.getUser().getSchool(),job);
//            em.getTransaction().commit();
//        }catch(Exception e){
//            System.out.println("database insertion error");
//            e.printStackTrace();
//        }
        try {
            // Retrieve the skills for the specified job
            String url = "https://jobsense.sg/api/get/job-skill-gind-for-g-job/";
            String urlParameters = "{ \"jobtitle\" : \"" + job.toLowerCase() + "\", \"industry_id\" : " + industryId + ", \"no_of_results\" : 10 }";

            JsonObject skillsResults = RestfulManager.sendPost(url, urlParameters);
            JsonArray arr = skillsResults.get("data").getAsJsonArray();
            Type listType = new TypeToken<List<String>>() {
            }.getType();

            List<String> yourList = new Gson().fromJson(arr, listType);
            skillArr = yourList;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        try {
            // Retrieve salary information for the specific job
            String url = "https://research.larc.smu.edu.sg/wagedb/api/get-salary-info/";
            String urlParameters = "jobtitle=" + job + "&industry=" + industry;

            JsonObject results = RestfulManager.sendPostWageDb(url, urlParameters);
            if (!results.get("status").getAsBoolean()) {
                min = 0;
                mean = 0;
                max = 0;
            }
            else {
                JsonObject salaryInfo = results.get("salary-info").getAsJsonObject().get("salary-info").getAsJsonObject();

                min = salaryInfo.get("min-salary").getAsInt();
                mean = salaryInfo.get("mean-salary").getAsInt();
                max = salaryInfo.get("max-salary").getAsInt();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        try {
            careerPath = new ArrayList<>();
            
            // Retrieve the skills for the specified job
            String url = "https://jobsense.sg/api/get/js-2-pop-career-path/";
            String urlParameters = "{ \"jobtitle\" : \"" + job.toLowerCase() + "\", \"industry_id\" : " + industryId + "}";
            
            JsonObject careerPathResults = RestfulManager.sendPost(url, urlParameters);
            if (careerPathResults.get("status").getAsBoolean()) {
                JsonArray careerPathList1 = careerPathResults.get("data").getAsJsonArray();
                JsonArray careerPathList2=careerPathList1.get(0).getAsJsonArray();
                for (JsonElement elem : careerPathList2) {
                    JsonObject career = elem.getAsJsonObject();
                    String jobtitle = career.get("jobtitle").getAsString();
                    
                    careerPath.add(StringManager.convertToTitleCaseIteratingChars(jobtitle));
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("JobDetails.jsf");
    }

    public void submitTrack() throws IOException {
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

            Map<String, Float> skillMap = new HashMap<>();
            Iterator<String> iterator = skillList.iterator();

            JaccardSimilarity similarity = new JaccardSimilarity();
            trackSkillList = new ArrayList<>();

            // scan through the skillList and populate the skills with a score into the map
            while (iterator.hasNext()) {
                String skillName = iterator.next();
                
                TopDocs results = LuceneManager.searchQuery(skillName);
                Float score = results.getMaxScore();

                String selectedSkill = "";
                ArrayList<String> skillScoreCheckList = new ArrayList<>();

                for (ScoreDoc doc : results.scoreDocs) {
                    int docId = doc.doc;

                    IndexReader reader = DirectoryReader.open(LuceneManager.index);
                    IndexSearcher searcher = new IndexSearcher(reader);

                    Document thisDoc = searcher.doc(docId);
                    String docSkillName = thisDoc.get("skill");

                    if (docSkillName.equalsIgnoreCase(skillName)) {
                        selectedSkill = docSkillName;
                        break;
                    } else {
                        skillScoreCheckList.add(docSkillName);
                    }
                }

                if (selectedSkill.isEmpty()) {
                    Double topScore = Double.MIN_VALUE;
                    for (String skill : skillScoreCheckList) {
                        Double individualScore = similarity.apply(skill, skillName);
                        if (individualScore > topScore) {
                            topScore = individualScore;
                            selectedSkill = skill;
                        }
                    }
                }

                trackSkillList.add(selectedSkill);

                if (score == null || score.isNaN()) {
                    skillMap.put(skillName, -1.0f);
                } else {
                    skillMap.put(skillName, score);
                }
            }

            // Sort the map in descending order based on the score value
            Map<String, Float> sortedSkillMap = skillMap
                    .entrySet()
                    .stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .collect(
                            Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                    LinkedHashMap::new));

            // Scan through the sortedSkillMap and convert it into a arraylist of custom
            ArrayList<Custom> mySkillList = new ArrayList<>();
            Iterator<String> skillIterator = sortedSkillMap.keySet().iterator();
            int index = 0;
//            topFifteenSkills = new ArrayList<>();

            while (skillIterator.hasNext()) {
                String skillName = skillIterator.next();
                Float score = sortedSkillMap.get(skillName);
                if (index++ <= 14) {
                    Custom custom = new Custom(true, skillName);
//                    topFifteenSkills.add(custom);
                    mySkillList.add(custom);
                } else {
                    Custom custom = new Custom(false, skillName);
                    mySkillList.add(custom);
                }
            }

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

            // Arrange the skills in each arraylist of 4 for achieved & unachieved skills
            achievedList = new ArrayList<>();
            unachievedList = new ArrayList<>();
            int achievedCount = 0, unachievedCount = 0;
            ArrayList<Custom> achievedTemp = null, unachievedTemp = null;

            for (Custom custom : mySkillList) {
                String name = custom.getName();
                // This current skill is an achieved skill
                if (userSkills.contains(name)) {
                    if (achievedCount == 4) {
                        achievedList.add(achievedTemp);
                        achievedCount = 0;
                    }
                    if (achievedCount == 0) {
                        achievedTemp = new ArrayList<>();
                    }
                    achievedTemp.add(custom);
                    achievedCount++;
                } // This current skill is NOT an achieved skill
                else {
                    if (unachievedCount == 4) {
                        unachievedList.add(unachievedTemp);
                        unachievedCount = 0;
                    }
                    if (unachievedCount == 0) {
                        unachievedTemp = new ArrayList<>();
                    }
                    unachievedTemp.add(custom);
                    unachievedCount++;
                }
            }

            if (achievedTemp != null && !achievedTemp.isEmpty()) {
                achievedList.add(achievedTemp);
            }

            if (unachievedTemp != null && !unachievedTemp.isEmpty()) {
                unachievedList.add(unachievedTemp);
            }

            //Return visualisable json data
            //Naming conflicts in jsonx.json library and gson.json library
            //the main logic is To retrieve relative course and skill information based on the track submitted
            //When the data is returned to TrackDetails.xhtml, a var object is used to get and store the json data on the frontend
            //The json data is then visualized using the imported dndTree.js in the <div = "tree-container">, visualization library is d3.js
            ArrayList<ArrayList<String>> courseInfo = new ArrayList<>();

            //Extract course name from the list of courses...still using java7, cannot method reference:(
            ArrayList<String> courseNameList = new ArrayList<>();
            for (Course course : courseList) {
                ArrayList<String> toAdd = new ArrayList<>();

                String courseId = course.getCourseId();
                String courseName = course.getCourseName();

                toAdd.add(courseId);
                toAdd.add(courseName);

                TypedQuery<String> relatedSkillQuery = em.createQuery("SELECT s.skillName FROM Skill s WHERE s.skillPK.courseId = ?1", String.class);
                relatedSkillQuery.setParameter(1, courseId);

                List<String> courseRelatedSkills = relatedSkillQuery.getResultList();
                for (String skillName : courseRelatedSkills) {
                    toAdd.add(skillName);
                }

                courseInfo.add(toAdd);
            }

            JsonArray toInclude = new JsonArray();
            for (ArrayList<String> detailedCourse : courseInfo) {
                String courseCode = detailedCourse.get(0);
                String courseName = detailedCourse.get(1);

                JsonArray skillSet = new JsonArray();
                for (int counter = 2; counter < detailedCourse.size(); counter++) {
                    //setting the size of a circle to cope for the frontend needs(e.g: bubble size)
                    int min = 1000;
                    int max = 2000;
                    int randomSize = ThreadLocalRandom.current().nextInt(min, max + 1);
                    JsonObject skillTemp = new JsonObject();
                    skillTemp.addProperty("name", detailedCourse.get(counter));
                    skillTemp.addProperty("size", randomSize);

                    skillSet.add(skillTemp);
                }

                JsonObject middleLevelCourse = new JsonObject();
                middleLevelCourse.addProperty("name", courseName);
                middleLevelCourse.add("children", skillSet);

                toInclude.add(middleLevelCourse);
            }

            JsonObject toReturnInfo = new JsonObject();
            toReturnInfo.addProperty("name", track);
            toReturnInfo.add("children", toInclude);

            courseInTree = toReturnInfo;

            JsonArray finalBubble = new JsonArray();
            ArrayList<String> flattenedAchievedSkill = new ArrayList<>();
            int bubbleMin = 30;
            int bubbleMax = 50;
            if (ownedSkills.size() > 0) {
                //old fashioned way to flatten a 2d array
                for (String curSkill : ownedSkills) {
                    int randomBubbleSize = ThreadLocalRandom.current().nextInt(bubbleMin, bubbleMax + 1);
                    JsonObject achievedSkillUnitToAdd = new JsonObject();
                    achievedSkillUnitToAdd.addProperty("text", curSkill);
                    achievedSkillUnitToAdd.addProperty("size", randomBubbleSize);
                    achievedSkillUnitToAdd.addProperty("group", 1);
                    finalBubble.add(achievedSkillUnitToAdd);
                }
            }
            //retrive unachievedskills from the remained map
            if (!map.isEmpty()) {
                Set<String> notPossessedSkills = map.keySet();
                for (String curSkill : notPossessedSkills) {
                    int randomBubbleSize = ThreadLocalRandom.current().nextInt(bubbleMin, bubbleMax + 1);
                    JsonObject unAchievedSkillUnitToAdd = new JsonObject();
                    unAchievedSkillUnitToAdd.addProperty("text", curSkill);
                    unAchievedSkillUnitToAdd.addProperty("size", randomBubbleSize);
                    unAchievedSkillUnitToAdd.addProperty("group", 2);
                    finalBubble.add(unAchievedSkillUnitToAdd);
                }
            }

            skillInBubble = finalBubble;
            
            //insert tracksearch data into database
            insertTrackSearchToAccessSummary(em, ub.getUser().getSchool(),track);
            em.getTransaction().commit();
            FacesContext.getCurrentInstance().getExternalContext().redirect("TrackDetails.jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getRelatedJobs() {
        List<SkillData> fullSkillData = DataService.retrieveSkillJobData();
        Map<String, Integer> checkListMap = new HashMap<>();
        List<JobData> checkList = new ArrayList<>();

        for (String skillName : trackSkillList) {
            SkillData skillData = fullSkillData.stream()
                    .filter(data -> skillName.equals(data.getSkill()))
                    .findAny()
                    .orElse(null);

            if (skillData != null) {
                List<JobData> jobDataList = skillData.getData();
                if (jobDataList != null && jobDataList.size() > 0) {
                    for (JobData thisJobData : jobDataList) {
                        String jobTitle = thisJobData.getJobtitle();
                        String jobIndustry = thisJobData.getIndustry();
                        List<String> skills = thisJobData.getSkills();
                        if (checkListMap.keySet().contains(jobTitle)) {
                            int num = checkListMap.get(jobTitle);
                            checkListMap.put(jobTitle, (num + 1));
                        } else {
                            checkListMap.put(jobTitle, 1);
                            checkList.add(thisJobData);
                        }
                    }
                }
            }
        }

        final Map<String, Integer> sortedByCount = checkListMap.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        
        List<JobData> finalList = new ArrayList<>();
        
        sortedByCount.keySet().forEach((jobTitle) -> {
            JobData jd = checkList.stream()
                    .filter(data -> jobTitle.equals(data.getJobtitle()))
                    .findAny()
                    .orElse(null);
            if (jd != null) {
                finalList.add(jd);
            }
        });
        skillDataList = finalList;
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
    
    public void hasSearchJobItem() {
        try {
            if (job == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("userhome.jsf");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    //insert user's job search data into database
    public void insertJobSearchToAccessSummary(EntityManager em, String school, String jobName){
        java.util.Date now = new Date();
        String accessType="J";
        AccessSummary as = new AccessSummary(now, school,accessType,null,jobName);
        em.persist(as);
        
        
    }
    //insert user's track search data into database
    public void insertTrackSearchToAccessSummary(EntityManager em, String school,String trackName){
        java.util.Date now = new Date();
        String accessType="T";
        AccessSummary as = new AccessSummary(now, school,accessType,trackName,null);
        em.persist(as);
    }

}
