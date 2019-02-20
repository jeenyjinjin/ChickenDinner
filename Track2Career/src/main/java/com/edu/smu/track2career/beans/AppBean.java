package com.edu.smu.track2career.beans;

import com.edu.smu.track2career.manager.DataService;
import com.edu.smu.track2career.manager.LuceneManager;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="app", eager=true)
@ApplicationScoped
public class AppBean {
    
    @PostConstruct
    public void init() {
        DataService.retrieveData();
//        DataService.storeSkillJobData();
        LuceneManager.startGatheringDocuments();
    }
    
}
