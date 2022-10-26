package com.channelfive.easyuni.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.channelfive.easyuni.constants.ExceptionMessages;
import com.channelfive.easyuni.entities.CourseIGP;
import com.channelfive.easyuni.entities.CourseIntake;
import com.channelfive.easyuni.exceptions.CourseIGPNotFoundException;
import com.channelfive.easyuni.exceptions.CourseIntakeNotFoundException;
import com.channelfive.easyuni.payload.JSONResponseBuilder;
import com.channelfive.easyuni.services.CourseDetailsService;

@CrossOrigin(origins = "http://localhost:8081", allowCredentials="true", maxAge = 3600)
@RestController
public class CourseDetailsController {

    @Autowired
    CourseDetailsService courseDetailsService;

    @GetMapping("/courseIGP/{courseName}")
    public ResponseEntity<?> getCourseIGP(@PathVariable String courseName) {
        String query = courseName.replace("-", " ");
        try {
            CourseIGP courseIGP = courseDetailsService.getCourseIGP(query);
            return ResponseEntity.ok().body(JSONResponseBuilder.build(true, null, courseIGP));
        } catch (CourseIGPNotFoundException e) {
            return ResponseEntity.ok().body(JSONResponseBuilder.build(false, e.getMessage(), query));
        } 
    }

    @GetMapping("/courseIntake/{courseName}")
    public ResponseEntity<?> getCourseIntake(@PathVariable String courseName) {
        String query = courseName.replace("-", " ");
        try {
            List<CourseIntake> courseIntake = courseDetailsService.getCourseIntake(query);
            if (courseIntake.size() == 0) throw new CourseIntakeNotFoundException(ExceptionMessages.CIT_NF_MSG);
            return ResponseEntity.ok().body(JSONResponseBuilder.build(true, null, courseIntake));
        } catch (CourseIntakeNotFoundException e) {
            return ResponseEntity.ok().body(JSONResponseBuilder.build(false, e.getMessage(), query));
        } 
    }
    
}
