package com.channelfive.easyuni.controllers;

import java.util.List;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
      return "account/login.html";
  }

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid LoginForm loginForm, HttpServletResponse response) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    AccountDetailsImpl accountDetailsImpl = (AccountDetailsImpl) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(accountDetailsImpl);

    List<String> roles = accountDetailsImpl.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());
        
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body("Login Successful");
        /*.body(new UserInfoResponse(accountDetailsImpl.getId(),
                                    accountDetailsImpl.getUsername(),
                                    accountDetailsImpl.getEmail(),
                                   roles));*/
  }

}