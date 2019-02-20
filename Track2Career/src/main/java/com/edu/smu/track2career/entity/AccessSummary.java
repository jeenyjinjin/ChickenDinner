/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.smu.track2career.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Xuhnx
 */
@Entity
@Table(name = "access_summary")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccessSummary.findAll", query = "SELECT a FROM AccessSummary a")
    , @NamedQuery(name = "AccessSummary.findByAccessTime", query = "SELECT a FROM AccessSummary a WHERE a.accessTime = :accessTime")})
public class AccessSummary implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name="access_time")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date accessTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "school")
    private String school;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "access_type")
    private String accessType;
    @Size(min = 1, max = 200)
    @Column(name = "track_name")
    private String trackName;
    @Size(min = 1, max = 200)
    @Column(name = "job_name")
    private String jobName;
    
    public AccessSummary(){
        
    }
    
    public AccessSummary(java.util.Date accessTime, String school,String accessType,String trackName,String jobName){
        this.accessTime=accessTime;
        this.school=school;
        this.accessType=accessType;
        this.trackName=trackName;
        this.jobName=jobName;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    
    
    
    
    
    
}
