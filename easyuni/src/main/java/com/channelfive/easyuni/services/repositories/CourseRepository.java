package com.channelfive.easyuni.services.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.channelfive.easyuni.entities.Course;

@Repository
public interface CourseRepository extends MongoRepository<Course, Long> {
    Optional<Course> findById(String id);
}
