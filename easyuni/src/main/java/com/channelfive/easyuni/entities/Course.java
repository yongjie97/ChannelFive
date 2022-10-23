package com.channelfive.easyuni.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "courses")
public class Course {
    
    @Id
    private String id;

    private String name;

    private String schoolId;

    @DocumentReference
    private List<CourseIGP> courseIGP; 

    @DocumentReference
    private List<CourseIntake> courseIntake;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CourseIGP> getCourseIGP() {
        return courseIGP;
    }

    public void setCourseIGP(List<CourseIGP> courseIGP) {
        this.courseIGP = courseIGP;
    }

    public List<CourseIntake> getCourseIntake() {
        return courseIntake;
    }

    public void setCourseIntake(List<CourseIntake> courseIntake) {
        this.courseIntake = courseIntake;
    }

    public String name() {
        return name;
    }

    public void setCourseName(String name) {
        this.name = name;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }


}
