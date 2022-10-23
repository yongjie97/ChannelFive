package com.channelfive.easyuni.services.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.channelfive.easyuni.entities.DiscussionReply;

@Repository
public interface DiscussionReplyRepository extends MongoRepository<DiscussionReply, String> {
    Optional<DiscussionReply> findById(String id);
}

