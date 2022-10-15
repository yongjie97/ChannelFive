package com.channelfive.easyuni.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.channelfive.easyuni.constants.ApplicationMessages;
import com.channelfive.easyuni.constants.ExceptionMessages;
import com.channelfive.easyuni.exceptions.UserNotVerifiedException;
import com.channelfive.easyuni.payload.JSONReponseBuilder;
import com.channelfive.easyuni.security.jwt.JwtUtils;
import com.channelfive.easyuni.services.implementations.AccountDetailsImpl;
import com.channelfive.easyuni.services.repositories.AccountRepository;
import com.channelfive.easyuni.validations.LoginForm;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class LoginController {
    
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  AccountRepository accountRepository;

  @Autowired
  JwtUtils jwtUtils;

  @GetMapping("/login")
  public String register(final Model model) {
      return "account/login";
  }

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
                .body(JSONReponseBuilder.build(true, ApplicationMessages.LOGIN_MSG, null));
        } catch (AuthenticationException e) {
            return ResponseEntity.ok().body(JSONReponseBuilder.build(false, ExceptionMessages.EMAIL_PW_WRONG_MSG, null));
        } catch (UserNotVerifiedException e) {
            SecurityContextHolder.clearContext();
            return ResponseEntity.ok().body(JSONReponseBuilder.build(false, e.getMessage(), null));
        }
    }

}