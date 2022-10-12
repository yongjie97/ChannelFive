package com.channelfive.easyuni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.channelfive.easyuni.entities.University;
import com.channelfive.easyuni.exceptions.UniversityNotFoundException;
import com.channelfive.easyuni.services.UniversityService;

@Controller
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @GetMapping("/university")
    public String index() {
        return "university/university.html";
    }

    @GetMapping("/api/university/{uni_name}")
    public ResponseEntity<?> universityInfo(@PathVariable String uni_name) {
        String query = uni_name.replace("-", " ");
        HttpHeaders headers = new HttpHeaders();
        try {
            University university = universityService.getUniversityInfo(query);
            headers.add("Location", "university.html");
            return new ResponseEntity<String>(headers, HttpStatus.OK);
        } catch (UniversityNotFoundException ex) {
            System.out.println(ex.getMessage());
            headers.add("Location", "error/404.html");  
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
    }
    
}
