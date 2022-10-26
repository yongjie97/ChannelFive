package com.channelfive.easyuni.validations;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RegisterForm {

    @Email(message = "Please provide a valid email id")
    private String email;

    @NotBlank(message = "Password can not be empty")
    private String password;

    private String address;

    private String displayName;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
    
}
