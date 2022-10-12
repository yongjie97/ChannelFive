package com.channelfive.easyuni.entities;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;

public class Course {

    @Id
    private int id;

    @NotBlank
    private String course_title;

    private int school_id;

    private String description;

    private int starting_salary;

    public Course(String name, int id){
        this.course_title = name;
        this.school_id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSalary(int salary){
        this.starting_salary = salary;
    }

    public int getSalary(){
        return starting_salary;
    }
    
}
