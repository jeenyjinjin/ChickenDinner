USE `track2career`;

INSERT INTO track (track_id, track_name)
VALUES ("T01", "Core"), ("T02", "Financial Technology"), ("T03", "Digital Business Solutioning"), 
("T04", "Business Analytics"), ("T05", "Software Development"), ("T06", "Cybersecurity");

INSERT INTO course (course_id, track_id, course_name, instructor_name)
VALUES ("IS112", "T01", "Data Management", "ZHENG Baihua"), ("IS110", "T01", "Information Systems & Innovation", "TANG Qian"), ("IS111", "T01", "Introduction to Programming", "JIANG Jing");

INSERT INTO skill (skill_id, skill_name, course_id)
VALUES ("DM01", "ER Modelling", "IS112"), ("DM02", "Data Normalization", "IS112"), ("DM01", "SQL", "IS112"),
	   ("ISI01", "Process Diagram", "IS110"), ("ISI02", "Business Process Innovation", "IS110"),
       ("IP01", "Data Structures", "IS111"), ("IP01", "File Handling", "IS111");
       
INSERT INTO software (software_id, software_name, course_id)
VALUES ("SW01", "mySQL", "IS112"), ("SW02", "Visio", "IS110"), ("SW03", "Python", "IS111");

INSERT INTO prerequisite_course (prerequisite_course_id, course_id)
VALUES ("IS111", IS112);

INSERT INTO user (user_id, user_type, username, password, email, school, track_id)
VALUES ("S1234567A", "student", "GuineaPig", "tester", "test@smu.edu.sg", "NUS"), ("S9325681G", "admin", "MoreLegit", "trustme", "mryandao@hotmail.com", "SMU", "T03");

INSERT INTO user_course (user_course_id, course_id, user_id) 
VALUES ("UC1", "IS112", "S1234567A"), ("UC2", "IS111", "SS9325681G");
	   