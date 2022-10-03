package com.channelfive.easyuni.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.channelfive.easyuni.constants.AccountRoleConstant;

@Document(collection = "accounts")
public class Account {
    
    @Id
    private String id;

    private Set<AccountRoleConstant> roles = new HashSet<>();

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String address;

    private LocalDateTime accountDate;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(LocalDateTime accountDate) {
        this.accountDate = accountDate;
    }

}
