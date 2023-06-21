package com.application.controller;

import com.application.service.*;
import com.application.model.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class GradeController {

    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/grades/{studentId}")
    @Secured("STUDENT")
    public ResponseEntity<?> getGradesForStudent(@PathVariable Long studentId) {
    	 try {
             List<Grade> grades = gradeService.getGradesByStudentId(studentId);
             return ResponseEntity.ok(grades);
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve grades for student");
         }
    }

    @PostMapping("/grades")
    @Secured("TEACHER")
    public ResponseEntity<?> addGrade(@RequestBody GradeRequest gradeRequest) {
        try {
            Grade grade = new Grade();
            grade.setId(gradeRequest.getStudentId());
            grade.setSubject(gradeRequest.getSubject());
            grade.setGrade(gradeRequest.getGrade());

            Grade savedGrade = gradeService.saveGrade(grade);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedGrade);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add grade");
        }
    }
    
    @PutMapping("/grades/{gradeId}")
    @Secured("TEACHER") // Require teacher role for access
    public ResponseEntity<?> updateGrade(@PathVariable Long gradeId, @RequestBody GradeRequest gradeRequest) {
    	try {
            if (!gradeService.existsById(gradeId)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grade not found");
            }

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String teacherUsername = authentication.getName();

            if (!gradeService.isTeacherAuthorizedForGrade(gradeId, teacherUsername)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized to update the grade");
            }

            Grade grade = gradeService.updateGrade(gradeId, gradeRequest);
            return ResponseEntity.ok(grade);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update the grade");
        }
    }

    @DeleteMapping("/grades/{gradeId}")
    @Secured("ROLE_TEACHER") 
    public ResponseEntity<?> deleteGrade(@PathVariable Long gradeId) {
    	try {
           
            if (!gradeService.existsById(gradeId)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grade not found");
            }

           
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String teacherUsername = authentication.getName();

            
            if (!gradeService.isTeacherAuthorizedForGrade(gradeId, teacherUsername)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized to delete the grade");
            }

          
            gradeService.deleteGrade(gradeId);
            return ResponseEntity.ok("Grade deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete the grade");
        }
    }
    
}

