package com.channelfive.easyuni.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courseIGP")
public class CourseIGP {

    @Id
    private String id;

    private int year;

    private String name;

    private String grades;

    private String gpa;

    private String rankPoint;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getRankPoint() {
        return rankPoint;
    }

    public void setRankPoint(String rankPoint) {
        this.rankPoint = rankPoint;
    }

}