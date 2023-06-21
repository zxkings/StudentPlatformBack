package com.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GradeRequest {
    private Long studentId;
    private String subject;
    private double grade;

    public GradeRequest() {
    }

    public GradeRequest(Long studentId, String subject, double grade) {
        this.studentId = studentId;
        this.subject = subject;
        this.grade = grade;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public ResponseEntity<String> toResponseEntity(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(message);
    }
}
