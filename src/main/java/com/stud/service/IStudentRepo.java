package com.stud.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stud.model.Student;


public interface IStudentRepo extends JpaRepository<Student, Integer> {
	

	
}
