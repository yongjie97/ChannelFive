package com.channelfive.easyuni.validations;

import javax.validation.constraints.Email;

public class ProfileForm {

    @Email
    private String email;

    private String address;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
