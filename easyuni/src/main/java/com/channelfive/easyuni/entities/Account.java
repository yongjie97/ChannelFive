package com.channelfive.easyuni.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.channelfive.easyuni.constants.AccountRoleConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "accounts")
public class Account {
    
    @Id
    private String id;

    @JsonIgnore
    private Set<AccountRoleConstant> roles = new HashSet<>();

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @JsonIgnore
    private String password;

    private String zipCode;

    private String displayName;

    @JsonIgnore
    private String verficationCode;

    private LocalDateTime accountDate;

    @JsonIgnore
    private String resetPasswordCode;

    public Account() {
        accountDate = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<AccountRoleConstant> getRoles() {
        return roles;
    }

    public void setRoles(Set<AccountRoleConstant> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getVerficationCode() {
        return verficationCode;
    }

    public void setVerficationCode(String verficationCode) {
        this.verficationCode = verficationCode;
    }

    public LocalDateTime getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(LocalDateTime accountDate) {
        this.accountDate = accountDate;
    }

    public String getResetPasswordCode() {
        return resetPasswordCode;
    }

    public void setResetPasswordCode(String resetPasswordCode) {
        this.resetPasswordCode = resetPasswordCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
