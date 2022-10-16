package com.channelfive.easyuni.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.channelfive.easyuni.constants.ExceptionMessages;
import com.channelfive.easyuni.entities.Account;
import com.channelfive.easyuni.exceptions.OldPasswordNotMatchException;
import com.channelfive.easyuni.exceptions.AccountNotFoundException;
import com.channelfive.easyuni.services.repositories.AccountRepository;
import com.channelfive.easyuni.validations.PasswordForm;
import com.channelfive.easyuni.validations.ProfileForm;
import com.channelfive.easyuni.validations.ResetPasswordForm;

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
        .orElseThrow(() -> new AccountNotFoundException(ExceptionMessages.ACC_NF_MSG));
        return account;
    }

    public void saveProfile(ProfileForm profileForm, String email) throws AccountNotFoundException {
        Account account = accountRepository.findByEmail(email)
        .orElseThrow(() -> new AccountNotFoundException(ExceptionMessages.ACC_NF_MSG));
        account.setZipCode(profileForm.getZipCode());
        accountRepository.save(account);
    }

    public void resetPassword(ResetPasswordForm resetPasswordForm) throws AccountNotFoundException {
        Account account = accountRepository.findByEmail(resetPasswordForm.getEmail())
            .orElseThrow(() -> new AccountNotFoundException(ExceptionMessages.ACC_NF_MSG));
        
        account.setResetPasswordCode(RandomStringUtils.randomAlphanumeric(64));
        accountRepository.save(account);
        // Send reset password email;
    }

    public void changePassword(PasswordForm passwordForm, String email) 
        throws AccountNotFoundException, OldPasswordNotMatchException {
            Account account = accountRepository.findByEmail(email)
            .orElseThrow(() -> new AccountNotFoundException(ExceptionMessages.ACC_NF_MSG));

            try {
                authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, passwordForm.getOldPassword()));
            } catch (Exception e) {
                throw new OldPasswordNotMatchException(ExceptionMessages.OLD_PW_NM_MSG);
            }
    
            String newPassword = passwordEncoder.encode(passwordForm.getNewPassword());
            account.setPassword(newPassword);
            accountRepository.save(account);
    }

}
