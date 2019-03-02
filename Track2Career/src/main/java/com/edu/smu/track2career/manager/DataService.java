package com.edu.smu.track2career.manager;

import com.edu.smu.track2career.entity.Course;
import com.edu.smu.track2career.entity.special.SkillData;
import com.google.gson.*;
import com.google.gson.reflect.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletContext;

public class DataService {

    private static List<String> skills;
    private static List<String> localSkills;

    public static void retrieveData() {
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String base = context.getRealPath(".");
        String folder = File.separator + "data";
        String file = File.separator + "data.txt";

        try (PrintStream ps = new PrintStream(new FileOutputStream(base + folder + file, false))) {
            String url = "https://jobsense.sg/api/get/js-2-valid-skill-list/";
            JsonObject result = RestfulManager.sendGet(url);
            JsonArray arr = result.get("data").getAsJsonArray();

            Type listType = new TypeToken<List<String>>() {
            }.getType();

            List<String> skillCorpus = new Gson().fromJson(arr, listType);

            skills = skillCorpus;

            for (String skill : skillCorpus) {
                ps.println(skill);
            }

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void storeSkillJobData() {
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        Thread thread = new Thread() {
            public void run() {
                String base = context.getRealPath(".");
                String folder = File.separator + "data";
                String file = File.separator + "skill_job_data.txt";

                JsonArray data = new JsonArray();

                skills.forEach((skill) -> {
                    JsonObject obj = new JsonObject();
                    obj.addProperty("skill", skill);
                    try {
                        String url = "https://jobsense.sg/api/get/js-2-job-w-skill-g-skill/";
                        String urlParameters = "{ \"skill\" : \"" + skill + "\", \"no_of_results\" : 10 }";
                        JsonObject result = RestfulManager.sendPost(url, urlParameters);
                        if (result.get("data").isJsonPrimitive() && result.get("data").getAsString().isEmpty()) {
                            throw new Exception("nothing");
                        }
                        JsonArray skillData = result.get("data").getAsJsonArray();
                        obj.add("data", skillData);
                    } catch (Exception ex) {
                        if (ex.getMessage().equals("404") || ex.getMessage().equals("nothing")) {
                            obj.add("data", new JsonArray());
                        }
                    }
                    data.add(obj);
                });

                try (PrintStream ps = new PrintStream(new FileOutputStream(base + folder + file, false))) {
                    ps.println(data.toString());
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public static List<SkillData> retrieveSkillJobData() {
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String base = context.getRealPath(".");
        String folder = File.separator + "data";
        String file = File.separator + "skill_job_data.txt";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(base + folder + file));
            Gson gson = new GsonBuilder().create();
            SkillData[] skillData = gson.fromJson(reader, SkillData[].class);
            List<SkillData> skillDataList = Arrays.asList(skillData);
            return skillDataList;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void retrieveAllLocalSkills() {
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String base = context.getRealPath(".");
        String folder = File.separator + "data";
        String file = File.separator + "localdata.txt";
        List<String> skillSets = null;
        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        
        try (PrintStream ps = new PrintStream(new FileOutputStream(base + folder + file, false))) {
            TypedQuery<String> skillQuery = em.createQuery("SELECT distinct s.skillName FROM Skill s ORDER BY s.skillName", String.class);
            skillSets = skillQuery.getResultList();
            localSkills = skillSets;
            
            for (String skill : skillSets) {
                ps.println(skill);
            }

            ps.close();
        }
        catch (Exception ex) {
            
        }
    }
}
