package com.channelfive.easyuni.services;

import java.util.List;

import com.channelfive.easyuni.entities.Course;
import com.channelfive.easyuni.entities.CourseIGP;

public interface CourseService {
    List<Course> searchCourseByTitle(String query);

    List<Course> searchCourseSortedBySalary(String query);

    //List<CourseIGP> searchCourseSortedByIGP(String query);

    Course createCourse(Course course);
}
