package com.channelfive.easyuni.services.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.channelfive.easyuni.entities.University;

@Repository
public interface UniversityRepository extends MongoRepository<University, Long> {
    Optional<University> findByName(String name);
}
