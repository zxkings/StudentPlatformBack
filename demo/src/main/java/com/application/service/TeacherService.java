package com.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.model.Teacher;
import com.application.repository.TeacherRepository;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void insertTeacher() {
    	if(teacherRepository.findByUsername("teacher") != null) {
    		return;
    	}
    	Teacher teacher= new Teacher("test2", "test2" , "teacher", "password");
        teacherRepository.save(teacher);
    }
}

