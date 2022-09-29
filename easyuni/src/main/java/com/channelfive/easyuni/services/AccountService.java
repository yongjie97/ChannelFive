package com.channelfive.easyuni.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.channelfive.easyuni.entities.Account;
import com.channelfive.easyuni.exceptions.UserAlreadyExistException;
import com.channelfive.easyuni.services.repositories.AccountRepository;
import com.channelfive.easyuni.validations.RegisterForm;

@Service("accountService")
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void register(RegisterForm registerForm) throws UserAlreadyExistException {
        //Let's check if user already registered with us
        if (checkIfUserExist(registerForm.getEmail())) {
            throw new UserAlreadyExistException("User already exists for this email");
        }
        Account accountEntity = new Account();
        BeanUtils.copyProperties(registerForm, accountEntity);
        encodePassword(accountEntity, registerForm);
        accountRepository.save(accountEntity);
    }

    public boolean checkIfUserExist(String email) {
        return accountRepository.findByEmail(email) !=null ? true : false;
    }

    private void encodePassword(Account accountEntity, RegisterForm registerForm) {
        accountEntity.setPassword(passwordEncoder.encode(registerForm.getPassword()));
    }

}
