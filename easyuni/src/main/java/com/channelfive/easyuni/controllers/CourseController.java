package com.channelfive.easyuni.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.channelfive.easyuni.entities.Course;
import com.channelfive.easyuni.entities.CourseIGP;
import com.channelfive.easyuni.services.CourseService;

@Controller
public class CourseController {
    
    @Autowired
    CourseService courseService;

    //search by title
    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCourseByTitle(@RequestParam("query") String query){
        /*try {
            courseService.searchCourseByTitle(query);
        } catch (CourseNotFoundException e) {
            return ResponseEntity.ok().body("Sorry! Course does not exist.");
        }*/
        return ResponseEntity.ok(courseService.searchCourseByTitle(query));
    }

    //search sorted by salary
    public ResponseEntity<List<Course>> searchCourseSortedBySalary(@RequestParam("query") String query){
        return ResponseEntity.ok(courseService.searchCourseSortedBySalary(query));
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course){
        return courseService.createCourse(course);
    }

    /*@GetMapping("/searchIGP")
    public ResponseEntity<List<CourseIGP>> searchCourseByIGP(@RequestParam("query") String query){
        try {
            courseService.searchCourseByTitle(query);
        } catch (CourseNotFoundException e) {
            return ResponseEntity.ok().body("Sorry! Course does not exist.");
        }
        return ResponseEntity.ok(courseService.searchCourseByIGP(query));
    }*/
}
