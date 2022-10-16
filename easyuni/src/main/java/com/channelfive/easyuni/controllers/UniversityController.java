package com.channelfive.easyuni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.channelfive.easyuni.entities.University;
import com.channelfive.easyuni.exceptions.UniversityNotFoundException;
import com.channelfive.easyuni.payload.JSONReponseBuilder;
import com.channelfive.easyuni.services.UniversityService;

@Controller
public class UniversityController {

    @Autowired
    UniversityService universityService;

    @GetMapping("/university/{uniName}")
    public ResponseEntity<?> index(@PathVariable String uniName) {
        String query = uniName.replace("-", " ");
        try {
            University university = universityService.getUniversityInfo(query);
            return ResponseEntity.ok().body(JSONReponseBuilder.build(true, null, university));
        } catch (UniversityNotFoundException e) {
            return ResponseEntity.ok().body(JSONReponseBuilder.build(false, e.getMessage(), query));
        }
    }

    @GetMapping("/api/university/{uni_name}")
    public ResponseEntity<?> universityInfo(@PathVariable String uni_name) {
        try {
            String query = uni_name.replace("-", " ");
            University university = universityService.getUniversityInfo(query);
            return ResponseEntity.ok().body(JSONReponseBuilder.build(true, null, university));
        } catch (UniversityNotFoundException e) {
            return ResponseEntity.ok().body(JSONReponseBuilder.build(false, e.getMessage(), null));
        }
    }
    
}
