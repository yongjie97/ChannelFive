package com.channelfive.easyuni.services.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.channelfive.easyuni.entities.Course;
import com.channelfive.easyuni.entities.CourseIGP;

@Repository
public interface CourseRepository extends MongoRepository<Course, Long> {

    Optional<Course> getCourseByName(String name);

    List<CourseIGP> getIGP(Course course);

}
