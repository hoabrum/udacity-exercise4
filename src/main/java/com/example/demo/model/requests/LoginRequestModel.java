package com.example.demo.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class LoginRequestModel implements Serializable {

    @JsonProperty("username")
    private String userName;
    @JsonProperty("password")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
