package com.channelfive.easyuni.services.implementations;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.channelfive.easyuni.entities.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class AccountDetailsImpl implements UserDetails {
    
private static final long serialVersionUID = 1L;

  private String id;

  private String email;

  private String displayName;

  private String zipCode;

  @JsonIgnore
  private String password;

  private LocalDateTime accountDate;

  private Collection<? extends GrantedAuthority> authorities;

  private Boolean verified;

  public AccountDetailsImpl(String id, String email, String displayName, String password, 
        LocalDateTime accountDate, Collection<? extends GrantedAuthority> authorities, Boolean verified) {
    this.id = id;
    this.email = email;
    this.displayName = displayName;
    this.password = password;
    this.accountDate = accountDate;
    this.authorities = authorities;
    this.verified = verified;
  }

  public static AccountDetailsImpl build(Account account) {
    List<GrantedAuthority> authorities = account.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(account.getRoles().toString()))
        .collect(Collectors.toList());

    return new AccountDetailsImpl(
        account.getId(), 
        account.getEmail(),
        account.getDisplayName(),
        account.getPassword(), 
        account.getAccountDate(),
        authorities,
        account.getVerficationCode() == null || account.getVerficationCode().isEmpty());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public String getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getDisplayName() {
    return displayName;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  public String getZipCode() {
    return zipCode;
  }

  public LocalDateTime getAccountDate() {
    return accountDate;
  }

  public boolean isVerified() {
    return verified;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
