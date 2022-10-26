package com.channelfive.easyuni.services;

import java.io.File;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.channelfive.easyuni.constants.AccountRoleConstant;
import com.channelfive.easyuni.constants.ExceptionMessages;
import com.channelfive.easyuni.entities.Account;
import com.channelfive.easyuni.exceptions.AccountNotFoundException;
import com.channelfive.easyuni.exceptions.InvalidVerificationCodeException;
import com.channelfive.easyuni.exceptions.OldPasswordNotMatchException;
import com.channelfive.easyuni.exceptions.UserAlreadyExistException;
import com.channelfive.easyuni.services.repositories.AccountRepository;
import com.channelfive.easyuni.validations.AccountRecoveryForm;
import com.channelfive.easyuni.validations.PasswordForm;
import com.channelfive.easyuni.validations.ProfileForm;
import com.channelfive.easyuni.validations.RegisterForm;
import com.channelfive.easyuni.validations.ResetPasswordForm;

@Service
public class AccountService {
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired 
    private EmailService emailService;

    public void register(RegisterForm registerForm) throws UserAlreadyExistException {
        if (checkIfUserExist(registerForm.getEmail()))
            throw new UserAlreadyExistException(ExceptionMessages.ACC_AE_MSG);

        Account accountEntity = new Account();
        BeanUtils.copyProperties(registerForm, accountEntity);
        accountEntity.setPassword(passwordEncoder.encode(registerForm.getPassword()));
        Set<AccountRoleConstant> roles = new HashSet<>();
        roles.add(AccountRoleConstant.USER);
        accountEntity.setRoles(roles);
        accountEntity.setVerficationCode(RandomStringUtils.randomAlphanumeric(64));
        accountRepository.save(accountEntity);
        //sendVerificationEmail(accountEntity.getEmail(), accountEntity.getVerficationCode());
        sendVerficationEmailHtml(accountEntity.getEmail(), accountEntity.getVerficationCode());
    }

    public void verifyUser(String email, String verificationCode) 
        throws AccountNotFoundException, InvalidVerificationCodeException {
            Account account = accountRepository.findByEmail(email) 
                .orElseThrow(() -> new AccountNotFoundException(ExceptionMessages.ACC_NF_MSG));

            if (!account.getVerficationCode().equals(verificationCode))
                throw new InvalidVerificationCodeException(ExceptionMessages.INVALID_VC_MSG);

            account.setVerficationCode(null);
            accountRepository.save(account);
    }

    public Account retrieveProfile(String email) throws AccountNotFoundException {
        Account account = accountRepository.findByEmail(email)
        .orElseThrow(() -> new AccountNotFoundException(ExceptionMessages.ACC_NF_MSG));
        return account;
    }

    public void saveProfile(ProfileForm profileForm, String email) throws AccountNotFoundException {
        Account account = accountRepository.findByEmail(email)
        .orElseThrow(() -> new AccountNotFoundException(ExceptionMessages.ACC_NF_MSG));
        account.setDisplayName(profileForm.getDisplayName());
        accountRepository.save(account);
    }

    public void accountRecovery(AccountRecoveryForm accountRecoveryForm) throws AccountNotFoundException {
        Account account = accountRepository.findByEmail(accountRecoveryForm.getEmail())
            .orElseThrow(() -> new AccountNotFoundException(ExceptionMessages.ACC_NF_MSG));
        
        account.setResetPasswordCode(RandomStringUtils.randomAlphanumeric(64));
        accountRepository.save(account);
        sendResetPasswordEmailHtml(account.getEmail(), account.getResetPasswordCode());
    }

    public void resetPassword(ResetPasswordForm resetPasswordForm, String code) throws AccountNotFoundException {
        Account account = accountRepository.findByResetPasswordCode(code)
        .orElseThrow(() -> new AccountNotFoundException(ExceptionMessages.ACC_NF_MSG));

        account.setPassword(passwordEncoder.encode(resetPasswordForm.getNewPassword()));
        account.setResetPasswordCode(null);
        accountRepository.save(account);
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

    public void verifyResetPasswordCode(String resetPasswordCode) throws InvalidVerificationCodeException {
        if (!accountRepository.existsByResetPasswordCode(resetPasswordCode))
            throw new InvalidVerificationCodeException(ExceptionMessages.INVALID_PVC_MSG);
    }

    private void sendResetPasswordEmailHtml(String email, String verificationCode) {
        String message = MessageFormat.format("{0}/{1}/{2}", 
        ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString(), 
        "password/recovery", verificationCode);
        try {
            File htmlPath = new ClassPathResource("static/email/change_password.html").getFile();
            String htmlBody = MessageFormat.format(Jsoup.parse(htmlPath).html(), message);
            emailService.sendHtmlMessage(email, "EasyUni Reset Password", htmlBody);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void sendVerficationEmailHtml(String email, String verificationCode) {
        String message = MessageFormat.format("{0}/{1}/{2}/{3}", 
        ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString(), 
        "register/verify", email, verificationCode);
        try {
            File htmlPath = new ClassPathResource("static/email/email.html").getFile();
            String htmlBody = MessageFormat.format(Jsoup.parse(htmlPath).html(), message);
            emailService.sendHtmlMessage(email, "EasyUni Verification Email", htmlBody);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean checkIfUserExist(String email) {
        return accountRepository.existsByEmail(email) == true ? true : false;
    }
    
}
