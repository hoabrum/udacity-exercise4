package com.example.demo.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class CreateUserRequest {

    @NotBlank
    @JsonProperty
    private String username;

    @NotBlank
    @JsonProperty
    @Length(min = 8, max = 25)
    private String password;

    @NotBlank
    @JsonProperty("confirm_password")
    @Length(min = 8, max = 25)
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
