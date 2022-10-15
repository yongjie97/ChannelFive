package com.channelfive.easyuni.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.channelfive.easyuni.constants.ApplicationMessages;
import com.channelfive.easyuni.exceptions.AccountNotFoundException;
import com.channelfive.easyuni.exceptions.InvalidVerificationCodeException;
import com.channelfive.easyuni.exceptions.UserAlreadyExistException;
import com.channelfive.easyuni.payload.JSONReponseBuilder;
import com.channelfive.easyuni.services.RegisterService;
import com.channelfive.easyuni.validations.RegisterForm;

@Controller
public class RegistrationController {

    @Autowired
    RegisterService registerService;

    @GetMapping("/register")
    public String register() {
        return "account/register";
    }

    @PostMapping("/register")
    public ResponseEntity<?> userRegistration(@Valid RegisterForm registerForm) {
        try {
            registerService.register(registerForm);
            return ResponseEntity.ok().body(JSONReponseBuilder.build(true, ApplicationMessages.S_REG_MSG, null)); 
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.ok().body(JSONReponseBuilder.build(false, e.getMessage(), null)); 
        }
    }

    @GetMapping("/register/verify/{email}/{verification_code}")
    public ResponseEntity<?> verifyAccount(@PathVariable String email, @PathVariable String verification_code) {
        try {
            registerService.verifyUser(email, verification_code);
            return ResponseEntity.ok().body(JSONReponseBuilder.build(true, ApplicationMessages.VERIFIED_MSG, null)); 
        } catch (InvalidVerificationCodeException e) {
            return ResponseEntity.ok().body(JSONReponseBuilder.build(false, e.getMessage(), null)); 
        } catch (AccountNotFoundException e) {
            return ResponseEntity.ok().body(JSONReponseBuilder.build(false, e.getMessage(), null)); 
        }
    }
}