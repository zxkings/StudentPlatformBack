package com.application.controller;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.service.AuthenticationService;
import com.application.model.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthenticationController {
  
  @Autowired
  private AuthenticationService authenticationService;

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
    String username = loginRequest.getUsername();
    String password = loginRequest.getPassword();    
    boolean isAuthenticated = authenticationService.login(username, password);
    
    if (isAuthenticated) {
      return ResponseEntity.ok("Login successful");
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
  }
  
}

