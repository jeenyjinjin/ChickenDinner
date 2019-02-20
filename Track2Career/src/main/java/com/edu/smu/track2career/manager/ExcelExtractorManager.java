package com.edu.smu.track2career.manager;

import com.edu.smu.track2career.entity.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class ExcelExtractorManager {

    private static LinkedHashMap<String, Integer> map;

    /**
     * description This method attempts to convert an uploaded stream object to
     * a XSSFWorkbook object
     *
     * @param input (java.io.InputStream)
     * @return org.​apache.​poi.​ss.​usermodel.Workbook
     * @throws IOException
     */
    public static Workbook streamToWorkbook(InputStream input) throws IOException {
        return new XSSFWorkbook(input);
    }

    public static int extractHeader(Sheet sheet) {
        map = new LinkedHashMap<>();
        boolean hasFoundHeader = false;

        int rowIndex = 0;

        while (!hasFoundHeader) {
            // Retrieve current row
            Row row = sheet.getRow(rowIndex++);

            // Retrieve the iterator object to iterate through the cells of the current row
            Iterator<Cell> cellIterator = row.iterator();

            // Iterate through all the cells in the current row
            while (cellIterator.hasNext()) {
                // Retrieve the current row object
                Cell currentCell = cellIterator.next();

                String value = currentCell.getStringCellValue();
                
                // Check if the cell value contains "As at"
                if (value.contains("As at")) {
                    break;
                }
                // Check if the cell value contains "Track / Faculty Advisor"
                if (value.toLowerCase().contains("track")) {
                    map.put("track", map.get("track") == null ? 1 : map.get("track") + 1);
                } else if (value.toLowerCase().contains("code")) {
                    map.put("courseCode", map.get("courseCode") == null ? 1 : map.get("courseCode") + 1);
                } else if (value.toLowerCase().contains("title")) {
                    map.put("courseTitle", map.get("courseTitle") == null ? 1 : map.get("courseTitle") + 1);
                } else if (value.toLowerCase().contains("coordinator")) {
                    map.put("courseCoordinator", map.get("courseCoordinator") == null ? 1 : map.get("courseCoordinator") + 1);
                } else if (value.toLowerCase().contains("skills competency")) {
                    map.put("skillsCompetency", map.get("skillsCompetency") == null ? 1 : map.get("skillsCompetency") + 1);
                } else if (value.toLowerCase().contains("software")) {
                    map.put("software", map.get("software") == null ? 1 : map.get("software") + 1);
                } else if (value.toLowerCase().contains("skillsfuture")) {
                    map.put("skillsfuture", map.get("skillsfuture") == null ? 1 : map.get("skillsfuture") + 1);
                } else if (value.toLowerCase().contains("description")) {
                    map.put("description", map.get("description") == null ? 1 : map.get("description") + 1);
                } else if (value.toLowerCase().contains("competency")) {
                    map.put("competency", map.get("competency") == null ? 1 : map.get("competency") + 1);
                } else if (value.toLowerCase().contains("keyword")) {
                    map.put("keyword", map.get("keyword") == null ? 1 : map.get("keyword") + 1);
                }
            }

            if (!map.isEmpty()) {
                hasFoundHeader = true;
            }
        }

        return rowIndex;
    }

    private static int getTotalCount() {
        Set<String> keys = map.keySet();
        Iterator<String> iter = keys.iterator();
        int count = 0;
        while (iter.hasNext()) {
            String key = iter.next();
            count += map.get(key);
        }
        return count;
    }

    public static void extractContent(Sheet sheet, int lastRowIndex, int currentRowIndex) {
        int totalCount = getTotalCount();

        int trackNo = 1;
        int skillNo = 1;
        int softwareNo = 1;

        Track trackObj = null;

        ArrayList<String> courseCodeList = new ArrayList<>();
        ArrayList<Track> trackList = new ArrayList<>();
        ArrayList<Course> courseList = new ArrayList<>();
        ArrayList<Skill> skillList = new ArrayList<>();
        ArrayList<Software> softwareList = new ArrayList<>();

        for (int rowIndex = currentRowIndex; rowIndex < lastRowIndex; rowIndex++) {
            Row row = sheet.getRow(rowIndex);

            Iterator<String> keys = map.keySet().iterator();
            int cellIndex = 0;

            String courseCode = "";
            String courseTitle = "";
            String courseCoord = "";
            List<String> skills = new ArrayList<>();
            List<String> softwares = new ArrayList<>();
            String description = "";
            outerloop:
            while (keys.hasNext()) {
                String key = keys.next();
                int count = map.get(key);

                for (int i = 0; i < count; i++) {
                    Cell currentCell = row.getCell(cellIndex++, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String value = currentCell.getStringCellValue();
                    
                    if (key.equals("track") && !value.isEmpty()) {
                        String track = value.contains("(") ? value.split("\\(")[0] : value;
                        track = track.replaceAll("\n", "");
                        track = track.trim();

                        String trackId = String.format("T%03d", trackNo++);

                        trackObj = new Track();
                        trackObj.setTrackId(trackId);
                        trackObj.setTrackName(track);

                        trackList.add(trackObj);
                    } else if (key.equals("courseCode") && value.isEmpty()) {
                        break outerloop;
                    } else if (key.equals("courseCode") && !value.isEmpty()) {
                        if (courseCodeList.contains(value)) {
                            break outerloop;
                        }
                        courseCodeList.add(value);
                        courseCode = value;
                    } else if (key.equals("courseTitle") && !value.isEmpty()) {
                        courseTitle = value;
                    } else if (key.equals("courseCoordinator") && value.isEmpty()) {
                        break outerloop;
                    } else if (key.equals("courseCoordinator") && !value.isEmpty()) {
                        courseCoord = value;
                    } else if (key.equals("skillsCompetency") || key.equals("competency") && !value.isEmpty()) {
//                        skills.add(value);
                    } else if (key.equals("software") && !value.isEmpty()) {
                        softwares.add(value);
                    } else if (key.equals("description") && !value.isEmpty()) {
                        description = value.trim();
//                        break;
                    } else if (key.equals("keyword") && !value.isEmpty()) {
                        skills.add(value);
                    }
                }
            }
            if (!courseCode.isEmpty() && !courseCoord.isEmpty()) {
                Course course = new Course();
                course.setCourseId(courseCode);
                course.setCourseName(courseTitle);
                course.setInstructorName(courseCoord);
                course.setDescription(description);
                course.setTrackId(trackObj);

                courseList.add(course);

                for (String skillName : skills) {
                    String skillId = String.format("SK%03d", skillNo++);
                    Skill skill = new Skill(skillId, courseCode);
                    skill.setSkillName(skillName);
                    skillList.add(skill);
                }

                for (String softwareName : softwares) {
                    String softwareId = String.format("SO%03d", softwareNo++);
                    Software software = new Software(softwareId, courseCode);
                    software.setSoftwareName(softwareName);
                    softwareList.add(software);
                }
            }
        }

        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();

        Query query1 = em.createQuery("DELETE FROM UserCourse");
        Query query2 = em.createQuery("DELETE FROM PrerequisiteCourse");
        Query query3 = em.createQuery("DELETE FROM Software");
        Query query4 = em.createQuery("DELETE FROM Skill");
        Query query5 = em.createQuery("DELETE FROM Course");
        Query query6 = em.createQuery("DELETE FROM Track");

        query1.executeUpdate();
        query2.executeUpdate();
        query3.executeUpdate();
        query4.executeUpdate();
        query5.executeUpdate();
        query6.executeUpdate();

        for (Track track : trackList) {
            em.persist(track);
        }

        for (Course course : courseList) {
            em.persist(course);
        }

        for (Skill skill : skillList) {
            em.persist(skill);
        }

        for (Software software : softwareList) {
            em.persist(software);
        }

        em.getTransaction().commit();
        em.close();
    }
}
