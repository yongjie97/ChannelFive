package com.channelfive.easyuni.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.channelfive.easyuni.security.jwt.JwtUtils;
import com.channelfive.easyuni.services.implementations.AccountDetailsImpl;
import com.channelfive.easyuni.services.repositories.AccountRepository;
import com.channelfive.easyuni.validations.LoginForm;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoginController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  AccountRepository accountRepository;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/login")
  public ResponseEntity<String> authenticateUser(final @Valid LoginForm loginForm, final BindingResult bindingResult, final Model model) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    AccountDetailsImpl accountDetailsImpl = (AccountDetailsImpl) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(accountDetailsImpl);

    List<String> roles = accountDetailsImpl.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(accountDetailsImpl.getAccountDate().toString());
        /*.body(new UserInfoResponse(accountDetailsImpl.getId(),
                                    accountDetailsImpl.getUsername(),
                                    accountDetailsImpl.getEmail(),
                                   roles));*/
  }
}