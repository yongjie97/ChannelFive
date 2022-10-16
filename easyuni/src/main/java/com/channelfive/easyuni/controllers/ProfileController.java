package com.channelfive.easyuni.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.channelfive.easyuni.constants.ApplicationMessages;
import com.channelfive.easyuni.entities.Account;
import com.channelfive.easyuni.exceptions.AccountNotFoundException;
import com.channelfive.easyuni.payload.JSONReponseBuilder;
import com.channelfive.easyuni.security.jwt.JwtUtils;
import com.channelfive.easyuni.services.ProfileService;
import com.channelfive.easyuni.validations.PasswordForm;
import com.channelfive.easyuni.validations.ProfileForm;
import com.channelfive.easyuni.validations.ResetPasswordForm;

@Controller
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/profile")
    public String viewProfile(HttpServletRequest request) {
        return "account/profile";
    }

    @GetMapping("/profile/retrieve")
    public ResponseEntity<?> retrieveProfile(HttpServletRequest request) {
        try {
            String token = jwtUtils.getJwtFromCookies(request);
            String email = jwtUtils.getEmailFromJwtToken(token);
            Account a = profileService.retrieveProfile(email);
            return ResponseEntity.ok().body(JSONReponseBuilder.build(true, null, a));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.ok().body(JSONReponseBuilder.build(false, e.getMessage(), null));
        }
    }

    @PutMapping("/profile/update")
    public ResponseEntity<?> updateProfile(@Valid ProfileForm profileForm, HttpServletRequest request) {
        try {
            String token = jwtUtils.getJwtFromCookies(request);
            String email = jwtUtils.getEmailFromJwtToken(token);
            profileService.saveProfile(profileForm, email);
            return ResponseEntity.ok().body(JSONReponseBuilder.build(true, ApplicationMessages.PUPDATED_MSG, null)); 
        } catch (AccountNotFoundException e) {
            return ResponseEntity.ok().body(JSONReponseBuilder.build(false, e.getMessage(), null)); 
        }
    }

    @GetMapping("/recovery")
    public String passwordRecovery() {
        return "account/forgot_password";
    }

    @PostMapping("/recovery")
    public ResponseEntity<?> resetPassword(@Valid ResetPasswordForm resetPasswordForm) {
        try {
            profileService.resetPassword(resetPasswordForm);
            return ResponseEntity.ok().body(JSONReponseBuilder.build(true, ApplicationMessages.R_PW_MSG, null)); 
        } catch (AccountNotFoundException e) {
            return ResponseEntity.ok().body(JSONReponseBuilder.build(false, e.getMessage(), null)); 
        }
    }

    @GetMapping("/change_password")
    public String viewChangePassword() {
        return "account/change_password";
    }

    @PostMapping("/profile/password")
    public ResponseEntity<?> changePassword(@Valid PasswordForm passwordForm, HttpServletRequest request) {
        try {
            String token = jwtUtils.getJwtFromCookies(request);
            String email = jwtUtils.getEmailFromJwtToken(token);
            profileService.changePassword(passwordForm, email);
            return ResponseEntity.ok().body(JSONReponseBuilder.build(true, ApplicationMessages.C_PW_MSG, null)); 
        } catch (Exception e) {
            return ResponseEntity.ok().body(JSONReponseBuilder.build(false, e.getMessage(), null)); 
        }
    }
    
}
