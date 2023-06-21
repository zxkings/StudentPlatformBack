package com.application.service;

import com.application.repository.GradeRepository;
import com.application.model.Grade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.controller.GradeRequest;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public List<Grade> getGradesByStudentId(Long studentId) {

        return gradeRepository.findByStudentId(studentId);
    }

	public boolean isTeacherAuthorizedForGrade(Long gradeId, String teacherUsername) {
		// TODO Auto-generated method stub
		return false;
	}

	public void deleteGrade(Long gradeId) {
		// TODO Auto-generated method stub
		
	}

	public boolean existsById(Long gradeId) {
		// TODO Auto-generated method stub
		return false;
	}

	public Grade updateGrade(Long gradeId, GradeRequest gradeRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}



