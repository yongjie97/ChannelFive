package com.channelfive.easyuni.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

import com.channelfive.easyuni.exceptions.AccountNotFoundException;
import com.channelfive.easyuni.exceptions.InvalidVerificationCodeException;
import com.channelfive.easyuni.exceptions.UserAlreadyExistException;
import com.channelfive.easyuni.services.RegisterService;
import com.channelfive.easyuni.validations.RegisterForm;

@Controller
public class RegistrationController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/register")
    public String register(final Model model){
        model.addAttribute("accountData", new RegisterForm());
        return "account/register.html";
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> userRegistration(@Valid RegisterForm registerForm) {
        /*if (bindingResult.hasErrors()) {
            model.addAttribute("registrationForm", registerForm);
            return ResponseEntity.ok("Invalid Form");
        }*/
        try {
            registerService.register(registerForm);
        } catch (UserAlreadyExistException e) {
            //bindingResult.rejectValue("email", "accountData.email","An account already exists for this email.");
            //model.addAttribute("registrationForm", registerForm);
            return ResponseEntity.ok().body("Email has been used already");
        }
        return ResponseEntity.ok().body("Successfully Registered");
    }

    @GetMapping("/register/verify/{email}/{verification_code}")
    public ResponseEntity<?> verifyAccount(@PathVariable String email, @PathVariable String verification_code) {
        try {
            registerService.verifyUser(email, verification_code);
            return ResponseEntity.ok("Account verified. You may now log in");
        } catch (InvalidVerificationCodeException e) {
            return ResponseEntity.ok(e.getMessage());
        } catch (AccountNotFoundException e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}