package com.channelfive.easyuni.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.channelfive.easyuni.entities.Account;
import com.channelfive.easyuni.exceptions.AccountNotFoundException;
import com.channelfive.easyuni.security.jwt.JwtUtils;
import com.channelfive.easyuni.services.ProfileService;
import com.channelfive.easyuni.validations.PasswordForm;
import com.channelfive.easyuni.validations.ProfileForm;

@Controller
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/profile")
    public ResponseEntity<?> viewProfile(HttpServletRequest request){
        try {
            String token = jwtUtils.getJwtFromCookies(request);
            String email = jwtUtils.getEmailFromJwtToken(token);
            Account a = profileService.retrieveProfile(email);
            return ResponseEntity.ok(a.getEmail() + "<br/>" + a.getPassword() + "<br/>" + a.getAddress() + "<br/>" + a.getAccountDate().toString());
        } catch (AccountNotFoundException e) {
            return ResponseEntity.ok(e.getMessage());
        }
        //return "account/register.html";
    }

    @PutMapping("/profile/update")
    public ResponseEntity<?> updateProfile(@Valid ProfileForm profileForm, HttpServletRequest request) {
        try {
            String token = jwtUtils.getJwtFromCookies(request);
            String email = jwtUtils.getEmailFromJwtToken(token);
            profileService.saveProfile(profileForm, email);
            return ResponseEntity.ok("Profile Successfully Updated");
        } catch (AccountNotFoundException e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PutMapping("/profile/password")
    public ResponseEntity<?> changePassword(@Valid PasswordForm passwordForm, HttpServletRequest request) {
        try {
            String token = jwtUtils.getJwtFromCookies(request);
            String email = jwtUtils.getEmailFromJwtToken(token);
            profileService.changePassword(passwordForm, email);
            return ResponseEntity.ok("Password Has Been Changed");
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
    
}
