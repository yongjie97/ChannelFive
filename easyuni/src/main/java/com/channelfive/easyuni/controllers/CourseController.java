package com.channelfive.easyuni.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.channelfive.easyuni.entities.Course;
import com.channelfive.easyuni.entities.CourseIGP;
import com.channelfive.easyuni.exceptions.CourseNotFoundException;
import com.channelfive.easyuni.payload.JSONReponseBuilder;
import com.channelfive.easyuni.services.CourseService;

@Controller
public class CourseController {
    
    @Autowired
    private CourseService courseService;

    @GetMapping("/course/{courseName}")
    public ResponseEntity<?> courseInfo(@PathVariable String course_name) {
        try {
            String query = course_name.replace("-", " ");
            Course course = courseService.getCourseInfo(query);
            return ResponseEntity.ok().body(JSONReponseBuilder.build(true, null, course));
        } catch (CourseNotFoundException e) {
            return ResponseEntity.ok().body(JSONReponseBuilder.build(false, e.getMessage(), null));
        }
    }

    @GetMapping("/course/{CourseName}/igp")
    public ResponseEntity<?> CourseIGPInfo(@PathVariable String course_name) {
        try {
            String query = course_name.replace("-", " ");
            Course course = courseService.getCourseInfo(query);
            List<CourseIGP> courseIGP = courseService.getIGP(course);
            return ResponseEntity.ok().body(JSONReponseBuilder.build(true, null, courseIGP));
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.ok().body(JSONReponseBuilder.build(false, e.getMessage(), null));
        }
    }
}
