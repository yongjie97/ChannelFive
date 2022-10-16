package com.channelfive.easyuni.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class Course {
    
    @Id
    private String id;

    private String courseName;

    private String schoolId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchool(String schoolId) {
        this.schoolId = schoolId;
    }

}
