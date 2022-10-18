package com.channelfive.easyuni.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courseIntake")
public class CourseIntake {
    
    @Id
    private String id;

    private int year;

    private Course course;

    private int intake;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Course getCourse_id() {
        return course;
    }

    public void setCourse_id(Course course) {
        this.course = course;
    }

    public int getIntake() {
        return intake;
    }

    public void setIntake(int intake) {
        this.intake = intake;
    }

    
}
