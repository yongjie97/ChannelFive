package com.channelfive.easyuni.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class Course {
    
    @Id
    private String id;

    private String courseName;

    private String schoolId;

    private List<CourseIGP> courseIGP; 

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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }


}
