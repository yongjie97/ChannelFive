package com.channelfive.easyuni.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.channelfive.easyuni.constants.AccountRoleConstant;
import com.channelfive.easyuni.entities.Account;
import com.channelfive.easyuni.exceptions.UserAlreadyExistException;
import com.channelfive.easyuni.services.repositories.AccountRepository;
import com.channelfive.easyuni.validations.RegisterForm;

@Service
public class RegisterService {
    
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
        accountEntity.setPassword(passwordEncoder.encode(registerForm.getPassword()));
        Set<AccountRoleConstant> roles = new HashSet<>();
        roles.add(AccountRoleConstant.USER);
        accountEntity.setRoles(roles);
        accountRepository.save(accountEntity);
    }

    public boolean checkIfUserExist(String email) {
        return accountRepository.existsByEmail(email) == true ? true : false;
    }
}
