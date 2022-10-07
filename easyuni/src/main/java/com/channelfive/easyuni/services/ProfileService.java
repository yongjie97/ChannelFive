package com.channelfive.easyuni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.channelfive.easyuni.entities.Account;
import com.channelfive.easyuni.exceptions.PasswordNotMatchException;
import com.channelfive.easyuni.exceptions.AccountNotFoundException;
import com.channelfive.easyuni.services.repositories.AccountRepository;
import com.channelfive.easyuni.validations.PasswordForm;
import com.channelfive.easyuni.validations.ProfileForm;

@Service
public class ProfileService {
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Account retrieveProfile(String email) throws AccountNotFoundException {
        Account account = accountRepository.findByEmail(email)
        .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return account;
    }

    public void saveProfile(ProfileForm profileForm, String email) throws AccountNotFoundException {
        Account account = accountRepository.findByEmail(email)
        .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        account.setAddress(profileForm.getAddress());
        accountRepository.save(account);
    }

    public void changePassword(PasswordForm passwordForm, String email) 
        throws AccountNotFoundException, PasswordNotMatchException {
            Account account = accountRepository.findByEmail(email)
            .orElseThrow(() -> new AccountNotFoundException("Account not found"));

            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, passwordForm.getOldPassword()));

            if (!authentication.isAuthenticated())
                throw new PasswordNotMatchException("Old password does not match");
    
            String newPassword = passwordEncoder.encode(passwordForm.getNewPassword());
            account.setPassword(newPassword);
            accountRepository.save(account);
    }

}
