package com.channelfive.easyuni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.channelfive.easyuni.entities.Account;
import com.channelfive.easyuni.services.implementations.AccountDetailsImpl;
import com.channelfive.easyuni.services.repositories.AccountRepository;

@Service
public class LoginService implements UserDetailsService {
    
    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      Account account = accountRepository.findByEmail(email)
          .orElseThrow(() -> new UsernameNotFoundException("Email/Password is wrong."));
  
      return AccountDetailsImpl.build(account);
    }
}
