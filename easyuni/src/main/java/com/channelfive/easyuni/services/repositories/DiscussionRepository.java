package com.channelfive.easyuni.services.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.channelfive.easyuni.entities.Discussion;

@Repository
public interface DiscussionRepository extends MongoRepository<Discussion, String> {
    Optional<Discussion> findById(String id);
}

