package com.application.repository;

import com.application.model.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByUsername(String username);
}

