package com.channelfive.easyuni.services;

import java.io.File;
import java.util.Scanner; 

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.channelfive.easyuni.constants.ExceptionMessages;
import com.channelfive.easyuni.entities.CourseIGP;
import com.channelfive.easyuni.entities.CourseIntake;
import com.channelfive.easyuni.exceptions.CourseIGPNotFoundException;
import com.channelfive.easyuni.exceptions.CourseIntakeNotFoundException;
import com.channelfive.easyuni.exceptions.GESNotFoundException;
import com.channelfive.easyuni.services.repositories.CourseIGPRepository;
import com.channelfive.easyuni.services.repositories.CourseIntakeRepository;

@Service
public class CourseDetailsService {

    @Autowired
    private CourseIGPRepository courseIGPRepository;

    @Autowired
    private CourseIntakeRepository courseIntakeRepository;

    public JSONObject getGES() throws GESNotFoundException {
        try {
            File file = new ClassPathResource("static/gesdata.txt").getFile();
            Scanner myReader = new Scanner(file);
            StringBuilder sb = new StringBuilder();
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              sb.append(data);
            }
            myReader.close();
            JSONObject jsonObject = new JSONObject(sb.toString());
            return jsonObject;
        } catch (Exception e) {
            throw new GESNotFoundException(e.getMessage());
        }
    }

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
