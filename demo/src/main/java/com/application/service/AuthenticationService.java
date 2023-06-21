package com.application.service;

import com.application.model.*;
import com.application.repository.StudentRepository;
import com.application.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private TeacherRepository teacherRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Student student = studentRepository.findByUsername(username);
    if (student != null) {
      return (UserDetails) student;
    }

    Teacher teacher = teacherRepository.findByUsername(username);
    if (teacher != null) {
      return (UserDetails) teacher;
    }

    throw new UsernameNotFoundException("User not found with username: " + username);
  }

  public boolean login(String username, String password) {
    UserDetails userDetails = loadUserByUsername(username);
    if (userDetails.getUsername().equals(username) && userDetails.getPassword().equals(password)) {
      // Login successful
      return true;
    }
    System.out.print("username = " + username + "\n");
    System.out.print("userDetails.getUsername() = " + userDetails.getUsername()+ "\n");
    System.out.print("password = " + password+ "\n");
    System.out.print("userDetails.getPassword() = " + userDetails.getPassword()+ "\n");
    // Login failed
    return false;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

