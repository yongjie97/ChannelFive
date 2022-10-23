package com.channelfive.easyuni.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courseIGP")
public class CourseIGP {
    
    @Id
    private String id;

    private int year;

    private String courseId;

    private String IGP10th;

    private String IGP90th;

    public int getyear() {
        return year;
    }

    public void setyear(int year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIGP10th() {
        return IGP10th;
    }

    public void setIGP10th(String iGP10th) {
        IGP10th = iGP10th;
    }

    public String getIGP90th() {
        return IGP90th;
    }

    public void setIGP90th(String iGP90th) {
        IGP90th = iGP90th;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    
}
