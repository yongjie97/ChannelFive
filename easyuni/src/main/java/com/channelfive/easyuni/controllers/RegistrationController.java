package com.channelfive.easyuni.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public ResponseEntity<String> userRegistration(final @Valid RegisterForm registerForm, final BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registrationForm", registerForm);
            return ResponseEntity.ok("Invalid Form");
        }
        try {
            registerService.register(registerForm);
        } catch (UserAlreadyExistException e) {
            bindingResult.rejectValue("email", "accountData.email","An account already exists for this email.");
            model.addAttribute("registrationForm", registerForm);
            return ResponseEntity.ok(e.getMessage());
        }
        return ResponseEntity.ok("Successfully Registered");
    }
}