package com.channelfive.easyuni.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.channelfive.easyuni.constants.ExceptionMessages;
import com.channelfive.easyuni.entities.Account;
import com.channelfive.easyuni.services.repositories.AccountRepository;

@Service
public class AccountDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(ExceptionMessages.ACC_NF_MSG));

        return AccountDetailsImpl.build(account);
    }

}
