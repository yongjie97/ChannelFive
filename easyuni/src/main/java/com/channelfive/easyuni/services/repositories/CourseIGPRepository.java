package com.channelfive.easyuni.services.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.channelfive.easyuni.entities.CourseIGP;

@Repository
public interface CourseIGPRepository extends MongoRepository<CourseIGP, String> {
    Optional<CourseIGP> findByNameIgnoreCase(String name);
}
