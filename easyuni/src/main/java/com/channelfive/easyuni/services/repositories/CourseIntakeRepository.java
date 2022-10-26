package com.channelfive.easyuni.services.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.channelfive.easyuni.entities.CourseIntake;

@Repository
public interface CourseIntakeRepository extends MongoRepository<CourseIntake, String> {
    Optional<List<CourseIntake>> findAllByNameIgnoreCaseOrderByYearAsc(String name);
}
