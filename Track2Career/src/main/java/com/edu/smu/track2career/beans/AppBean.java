package com.edu.smu.track2career.beans;

import com.edu.smu.track2career.manager.DataService;
import com.edu.smu.track2career.manager.LocalLuceneManager;
import com.edu.smu.track2career.manager.LuceneManager;
import com.edu.smu.track2career.manager.RestfulManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="app", eager=true)
@ApplicationScoped
public class AppBean {
    
    @PostConstruct
    public void init() {
        // Retrieve all jobsense skills
        DataService.retrieveData();
        // Using all skills retrieved from jobsense, place them in lucenemanager to let it start working
        LuceneManager.startGatheringDocuments();
        
        // Retrieve all local db skills
        DataService.retrieveAllLocalSkills();
        // Using all local db skills retrieved, place them in locallucenemanager to let it start working
        LocalLuceneManager.startGatheringDocuments();
        
//        DataService.storeSkillJobData();
        
        // Retrieve all valid industry from jobsense and initialize another instance of lucene for industry search
        try {
            String url = "https://jobsense.sg/api/get/js-2-valid-ind-list/";
            JsonObject results = RestfulManager.sendGet(url);
            JsonArray dataArr = results.get("data").getAsJsonArray();
            Set<String> industryList = new TreeSet<>();
            for (int i = 0; i < dataArr.size(); i++) {
                String industry = dataArr.get(i).getAsString();
                if (!industry.trim().isEmpty()) {
                    industry = industry.trim();
                    industryList.add(industry);
                }
            }
            List<String> industries = new ArrayList<>(industryList);
            LuceneManager.initializeIndustryAnalyzer(industries);
        }
        catch (Exception e) {
            
        }
    }
    
}
