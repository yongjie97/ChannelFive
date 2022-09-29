package com.channelfive.easyuni.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.channelfive.easyuni.exceptions.UserAlreadyExistException;
import com.channelfive.easyuni.validations.RegisterForm;

@Controller
public class LoginController {
    
    /*@PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> userRegistration(final @Valid AccountForm loginForm, final BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registrationForm", loginForm);
            return ResponseEntity.ok("Invalid Form");
        }
        try {
            accountService.register(registerForm);
        } catch (UserAlreadyExistException e) {
            bindingResult.rejectValue("email", "accountData.email","An account already exists for this email.");
            model.addAttribute("registrationForm", registerForm);
            return ResponseEntity.ok(e.getMessage());
        }
        return ResponseEntity.ok("Successfully Registered");
    }*/

}
