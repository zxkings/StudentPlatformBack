package com.application;


import java.util.*;
import java.util.stream.Stream;

import com.application.model.*;
import com.application.repository.TeacherRepository;
import com.application.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@RestController
public class backend implements CommandLineRunner {	
	
//	private final TeacherRepository teacherRepository;
//	private final StudentRepository studentRepository;
//	private final GradeRepository gradeRepository;
	
	private final TeacherService teacherService;

    @Autowired
    public backend(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
	
	 public static void main(String[] args) {		 
		 SpringApplication.run(backend.class, args);
		 
//		 Student student = new Student("test1", "test1" , "student", "password");
//		 Teacher teacher= new Teacher("test2", "test2" , "teacher", "password");
//		 Grade grade = new Grade(student, "math", 12);
		 
//		 studentRepository.save(student);
//		 teacherRepository.save(teacher);	
//		 gradeRepository.save(grade);
		 
    }

	@Override
	public void run(String... args) throws Exception {
		teacherService.insertTeacher();
		
	}
}
