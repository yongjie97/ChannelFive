package com.channelfive.easyuni.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.channelfive.easyuni.constants.ApplicationMessages;
import com.channelfive.easyuni.constants.ExceptionMessages;
import com.channelfive.easyuni.entities.Account;
import com.channelfive.easyuni.exceptions.AccountNotFoundException;
import com.channelfive.easyuni.exceptions.InvalidVerificationCodeException;
import com.channelfive.easyuni.exceptions.UserAlreadyExistException;
import com.channelfive.easyuni.exceptions.UserNotVerifiedException;
import com.channelfive.easyuni.payload.JSONResponseBuilder;
import com.channelfive.easyuni.security.jwt.JwtUtils;
import com.channelfive.easyuni.services.AccountService;
import com.channelfive.easyuni.services.EmailService;
import com.channelfive.easyuni.services.implementations.AccountDetailsImpl;
import com.channelfive.easyuni.services.repositories.AccountRepository;
import com.channelfive.easyuni.validations.LoginForm;
import com.channelfive.easyuni.validations.PasswordForm;
import com.channelfive.easyuni.validations.ProfileForm;
import com.channelfive.easyuni.validations.RegisterForm;
import com.channelfive.easyuni.validations.ResetPasswordForm;
import com.channelfive.easyuni.validations.AccountRecoveryForm;

@CrossOrigin(origins = "http://localhost:8081", allowCredentials="true", maxAge = 3600)
@RestController
public class AccountController {

    @Autowired
    AuthenticationManager authenticationManager;
  
    @Autowired
    AccountRepository accountRepository;
  
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    EmailService emailService;

    @Autowired
    AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid LoginForm loginForm, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            AccountDetailsImpl accountDetailsImpl = (AccountDetailsImpl) authentication.getPrincipal();

            if (!accountDetailsImpl.isVerified()) {
                throw new UserNotVerifiedException(ExceptionMessages.ACC_NV_MSG);
            }

            ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(accountDetailsImpl);

            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(JSONResponseBuilder.build(true, ApplicationMessages.LOGIN_MSG, null));
        } catch (AuthenticationException e) {
            return ResponseEntity.ok().body(JSONResponseBuilder.build(false, ExceptionMessages.EMAIL_PW_WRONG_MSG, null));
        } catch (UserNotVerifiedException e) {
            SecurityContextHolder.clearContext();
            return ResponseEntity.ok().body(JSONResponseBuilder.build(false, e.getMessage(), null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> userRegistration(@Valid RegisterForm registerForm) {
        try {
            accountService.register(registerForm);
            return ResponseEntity.ok().body(JSONResponseBuilder.build(true, ApplicationMessages.S_REG_MSG, null)); 
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.ok().body(JSONResponseBuilder.build(false, e.getMessage(), null)); 
        }
    }

    @GetMapping("/register/verify/{email}/{verification_code}")
    public ResponseEntity<?> verifyAccount(@PathVariable String email, @PathVariable String verification_code) {
        try {
            accountService.verifyUser(email, verification_code);
            return ResponseEntity.ok().body(JSONResponseBuilder.build(true, ApplicationMessages.VERIFIED_MSG, null)); 
        } catch (InvalidVerificationCodeException e) {
            return ResponseEntity.ok().body(JSONResponseBuilder.build(false, e.getMessage(), null)); 
        } catch (AccountNotFoundException e) {
            return ResponseEntity.ok().body(JSONResponseBuilder.build(false, e.getMessage(), null)); 
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> retrieveProfile(HttpServletRequest request) {
        try {
            String token = jwtUtils.getJwtFromCookies(request);
            String email = jwtUtils.getEmailFromJwtToken(token);
            Account a = accountService.retrieveProfile(email);
            return ResponseEntity.ok().body(JSONResponseBuilder.build(true, null, a));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.ok().body(JSONResponseBuilder.build(false, e.getMessage(), null));
        }
    }

    @PostMapping("/profile/update")
    public ResponseEntity<?> updateProfile(@Valid ProfileForm profileForm, HttpServletRequest request) {
        try { 
            String token = jwtUtils.getJwtFromCookies(request);
            String email = jwtUtils.getEmailFromJwtToken(token);
            accountService.saveProfile(profileForm, email);
            return ResponseEntity.ok().body(JSONResponseBuilder.build(true, ApplicationMessages.PUPDATED_MSG, null)); 
        } catch (AccountNotFoundException e) {
            return ResponseEntity.ok().body(JSONResponseBuilder.build(false, e.getMessage(), null)); 
        }
    }

    @PostMapping("/password/recovery")
    public ResponseEntity<?> resetPassword(@Valid AccountRecoveryForm resetPasswordForm) {
        try {
            accountService.accountRecovery(resetPasswordForm);
            return ResponseEntity.ok().body(JSONResponseBuilder.build(true, ApplicationMessages.R_PW_MSG, null)); 
        } catch (AccountNotFoundException e) {
            return ResponseEntity.ok().body(JSONResponseBuilder.build(false, e.getMessage(), null)); 
        }
    }

    @GetMapping("/password/recovery/{code}")
    public ResponseEntity<?> verifyAccount(@PathVariable String code) {
        try {
            accountService.verifyResetPasswordCode(code);
            return ResponseEntity.ok().body(JSONResponseBuilder.build(true, ApplicationMessages.R_PW_MSG, null)); 
        } catch (InvalidVerificationCodeException e) {
            return ResponseEntity.ok().body(JSONResponseBuilder.build(false, e.getMessage(), null)); 
        }
    }

    @PostMapping("/password/recovery/{code}")
    public ResponseEntity<?> verifyAccount(ResetPasswordForm resetPasswordForm, @PathVariable String code) {
        try {
            accountService.resetPassword(resetPasswordForm, code);
            return ResponseEntity.ok().body(JSONResponseBuilder.build(true, ApplicationMessages.R_PW_S_MSG, null)); 
        } catch (AccountNotFoundException e) {
            return ResponseEntity.ok().body(JSONResponseBuilder.build(false, e.getMessage(), null)); 
        }
    }

    @PostMapping("/profile/password")
    public ResponseEntity<?> changePassword(@Valid PasswordForm passwordForm, HttpServletRequest request) {
        try {
            String token = jwtUtils.getJwtFromCookies(request);
            String email = jwtUtils.getEmailFromJwtToken(token);
            accountService.changePassword(passwordForm, email);
            return ResponseEntity.ok().body(JSONResponseBuilder.build(true, ApplicationMessages.C_PW_MSG, null)); 
        } catch (Exception e) {
            return ResponseEntity.ok().body(JSONResponseBuilder.build(false, e.getMessage(), null)); 
        }
    }
    
}
