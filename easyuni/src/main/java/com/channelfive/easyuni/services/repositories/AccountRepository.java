package com.channelfive.easyuni.services.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.channelfive.easyuni.entities.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, Long>{
    Account findByEmail(String email);
}
