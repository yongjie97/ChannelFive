package com.channelfive.easyuni.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.channelfive.easyuni.constants.ExceptionMessages;
import com.channelfive.easyuni.entities.CourseIGP;
import com.channelfive.easyuni.entities.CourseIntake;
import com.channelfive.easyuni.exceptions.CourseIGPNotFoundException;
import com.channelfive.easyuni.exceptions.CourseIntakeNotFoundException;
import com.channelfive.easyuni.services.repositories.CourseIGPRepository;
import com.channelfive.easyuni.services.repositories.CourseIntakeRepository;

@Service
public class CourseDetailsService {

    @Autowired
    private CourseIGPRepository courseIGPRepository;

    @Autowired
    private CourseIntakeRepository courseIntakeRepository;

    public CourseIGP getCourseIGP(String courseName) throws CourseIGPNotFoundException {
        CourseIGP courseIGP = courseIGPRepository.findByNameIgnoreCase(courseName)
                .orElseThrow(() -> new CourseIGPNotFoundException(ExceptionMessages.CIGP_NF_MSG));

        return courseIGP;
    }

    public List<CourseIntake> getCourseIntake(String courseName) throws CourseIntakeNotFoundException {
        List<CourseIntake> courseIntake = courseIntakeRepository.findAllByNameIgnoreCaseOrderByYearAsc(courseName)
                .orElseThrow(() -> new CourseIntakeNotFoundException(ExceptionMessages.CIT_NF_MSG));

        return courseIntake;
    }

}
