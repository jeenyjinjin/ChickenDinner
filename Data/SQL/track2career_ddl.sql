/* Drop database if exists */
DROP DATABASE IF EXISTS `track2career`;

/* Create database */
CREATE DATABASE `track2career`;

/* Use the created database */
USE `track2career`;

/* Table creation STARTS */
CREATE TABLE track (
	track_id CHAR(20) NOT NULL,
	track_name VARCHAR(200) NOT NULL,
	
	CONSTRAINT track_pk PRIMARY KEY (track_id)
);

CREATE TABLE course (
	course_id VARCHAR(10) NOT NULL,
	track_id CHAR(20) NOT NULL,
	course_name VARCHAR(200) NOT NULL,
	instructor_name VARCHAR(100) NOT NULL,
	
	CONSTRAINT course_pk PRIMARY KEY (course_id),
	CONSTRAINT course_fk FOREIGN KEY (track_id) REFERENCES track (track_id)
);

CREATE TABLE skill (
	skill_id CHAR(5) NOT NULL,
	skill_name VARCHAR(200) NOT NULL,
	course_id VARCHAR(10) NOT NULL,
	
	CONSTRAINT skill_pk PRIMARY KEY (course_id, skill_id),
	CONSTRAINT skill_fk FOREIGN KEY (course_id) REFERENCES course (course_id)
);

CREATE TABLE software (
	software_id CHAR(5) NOT NULL,
	software_name VARCHAR(200) NOT NULL,
	course_id VARCHAR(10) NOT NULL,
	
	CONSTRAINT software_pk PRIMARY KEY (course_id, software_id),
	CONSTRAINT software_fk FOREIGN KEY (course_id) REFERENCES course (course_id)
);

CREATE TABLE prerequisite_course (
	prerequisite_course_id CHAR(26) NOT NULL,
	course_id VARCHAR(10) NOT NULL,
	
	CONSTRAINT prerequisite_course_pk PRIMARY KEY (course_id, prerequisite_course_id),
	CONSTRAINT prerequisite_course_fk FOREIGN KEY (course_id) REFERENCES course (course_id)
);

CREATE TABLE user (
	user_id VARCHAR(20) NOT NULL,
	username VARCHAR(40) NOT NULL,
	password VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	school VARCHAR(100) NOT NULL,
	track_id CHAR(20),
	
	CONSTRAINT user_pk PRIMARY KEY (user_id),
	CONSTRAINT user_fk FOREIGN KEY (track_id) REFERENCES track (track_id)
);

CREATE TABLE user_course (
	user_course_id CHAR(26) NOT NULL,
	course_id VARCHAR(10) NOT NULL,
	user_id VARCHAR(20) NOT NULL,
	
	CONSTRAINT user_course_pk PRIMARY KEY (user_id, course_id, user_course_id),
	CONSTRAINT user_course_fk1 FOREIGN KEY (user_id) REFERENCES user (user_id),
	CONSTRAINT user_course_fk2 FOREIGN KEY (course_id) REFERENCES course (course_id)
);