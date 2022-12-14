package com.channelfive.easyuni.services.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.channelfive.easyuni.entities.Course;

@Repository
public interface CourseRepository extends MongoRepository<Course, Long> {
    
    List<Course> findByCourseNameLike(String query);

    @Query(value = "{course_title:?0}",  sort= "{salary:1}") //sort by salary
    List<Course> searchCourseSortedBySalary(String query);

    //@Query(value = "{course_title:?0}",  sort= "{IGP:1}")
    //List<Course> searchCourseSortedByIGP(String query);

}
