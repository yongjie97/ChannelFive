package com.channelfive.easyuni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.channelfive.easyuni.constants.ExceptionMessages;
import com.channelfive.easyuni.entities.University;
import com.channelfive.easyuni.exceptions.UniversityNotFoundException;
import com.channelfive.easyuni.services.repositories.UniversityRepository;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    public University getUniversityInfo(String name) throws UniversityNotFoundException {
        University university = universityRepository.findByNameIgnoreCase(name)
            .orElseThrow(() -> new UniversityNotFoundException(ExceptionMessages.UNI_NF_MSG));
        return university;
    }
    
}
