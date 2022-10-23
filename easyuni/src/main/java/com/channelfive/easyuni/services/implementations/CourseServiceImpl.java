package com.channelfive.easyuni.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.channelfive.easyuni.entities.Course;
import com.channelfive.easyuni.services.CourseService;
import com.channelfive.easyuni.services.repositories.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
    
    private CourseRepository courseRepository;
    
    public CourseServiceImpl(CourseRepository CourseRepo) {
        this.courseRepository = CourseRepo;
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> searchCourseByTitle(String query) {
        List<Course> course = courseRepository.findByCourseNameLike(query);
        return course;
    }

    @Override
    public List<Course> searchCourseSortedBySalary(String query) {
        List<Course> course = courseRepository.searchCourseSortedBySalary(query);
        return course;
    }

    
}
