package com.channelfive.easyuni.services.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.channelfive.easyuni.entities.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, Long>{
    Optional<Account> findByEmail(String email);
    Boolean existsByEmail(String email);
}
