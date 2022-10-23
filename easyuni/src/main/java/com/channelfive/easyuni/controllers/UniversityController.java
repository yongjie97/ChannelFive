package com.channelfive.easyuni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.channelfive.easyuni.entities.University;
import com.channelfive.easyuni.exceptions.UniversityNotFoundException;
import com.channelfive.easyuni.payload.JSONResponseBuilder;
import com.channelfive.easyuni.services.UniversityService;

@CrossOrigin(origins = "http://localhost:8081", allowCredentials="true", maxAge = 3600)
@RestController
public class UniversityController {

    @Autowired
    UniversityService universityService;

    @GetMapping("/university/{uniName}")
    public ResponseEntity<?> index(@PathVariable String uniName) {
        String query = uniName.replace("-", " ");
        try {
            University university = universityService.getUniversityInfo(query);
            return ResponseEntity.ok().body(JSONResponseBuilder.build(true, null, university));
        } catch (UniversityNotFoundException e) {
            return ResponseEntity.ok().body(JSONResponseBuilder.build(false, e.getMessage(), query));
        } 
    }
    
}
