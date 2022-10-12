package com.channelfive.easyuni.services;

import java.util.List;

import com.channelfive.easyuni.entities.Course;

public interface CourseService {
    List<Course> searchCourseByTitle(String query);

    List<Course> searchCourseSortedBySalary(String query);

    Course createCourse(Course course);
}
