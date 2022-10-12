package com.channelfive.easyuni.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.channelfive.easyuni.entities.University;
import com.channelfive.easyuni.exceptions.UniversityNotFoundException;
import com.channelfive.easyuni.services.repositories.UniversityRepository;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    public University getUniversityInfo(String name) throws UniversityNotFoundException {
        University university = universityRepository.findByName(name)
            .orElseThrow(() -> new UniversityNotFoundException("University not found."));
        return university;
    }
    
}
