package com.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.application.model.UserType;

import com.application.model.*;

public class LoginRequest {
    private String username;
    private String password;
    private UserType userType;


    public LoginRequest() {
    }
    
    public LoginRequest(String username, String password, UserType userType) {
    	this.username = username;
    	this.password = password;
    	this.userType = userType;
    }

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
    
    public UserType getUserType() {
        return userType;
      }

      public void setUserType(UserType userType) {
        this.userType = userType;
      }
    
    public ResponseEntity<String> toResponseEntity(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(message);
    }
}

