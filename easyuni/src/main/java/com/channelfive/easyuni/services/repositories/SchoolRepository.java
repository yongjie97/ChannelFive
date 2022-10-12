package com.channelfive.easyuni.services.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.channelfive.easyuni.entities.School;

@Repository
public interface SchoolRepository extends MongoRepository<School, String> {
    Optional<School> findById(String id);
}
