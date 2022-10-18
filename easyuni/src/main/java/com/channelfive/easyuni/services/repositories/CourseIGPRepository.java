package com.channelfive.easyuni.services.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.channelfive.easyuni.entities.Course;
import com.channelfive.easyuni.entities.CourseIGP;

@Repository
public interface CourseIGPRepository extends MongoRepository<CourseIGP, Long> {

    @Query("{course_igp:?0}") //search by title
    List<Course> searchCourseSortedByIGP(String query);

}
    

