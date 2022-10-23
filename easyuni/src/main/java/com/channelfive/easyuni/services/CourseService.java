package com.channelfive.easyuni.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.channelfive.easyuni.constants.ExceptionMessages;
import com.channelfive.easyuni.entities.Course;
import com.channelfive.easyuni.entities.CourseIGP;
import com.channelfive.easyuni.exceptions.CourseNotFoundException;
import com.channelfive.easyuni.services.repositories.CourseRepository;

@Service
public class CourseService {

    @Autowired 
    private CourseRepository courseRepository;

    public Course getCourseInfo(String name) throws CourseNotFoundException {
        Course course = courseRepository.getCourseByName(name)
            .orElseThrow(() -> new CourseNotFoundException(ExceptionMessages.COURSE_NF_MSG));
        return course;
    }

    public List<CourseIGP> getIGP(Course course) {
        List<CourseIGP> courseIGP = course.getCourseIGP();
        //List<CourseIGP> courseIGP = course.getCourseIGP();
        return courseIGP;
    }
}