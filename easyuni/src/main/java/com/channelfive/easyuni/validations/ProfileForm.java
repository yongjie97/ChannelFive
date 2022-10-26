package com.channelfive.easyuni.validations;

import javax.validation.constraints.Email;

public class ProfileForm {

    @Email
    private String email;

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
    
}
