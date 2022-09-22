package com.stud.service;

import java.util.List;
import java.util.Optional;

import com.stud.model.Student;

public interface IStudentService {

	Integer saveStudent(Student student);
	
	public List<Student> getAllStudent();
	
	Optional<Student> getStudent(Integer Id);
	
	public void deleteStudent(Integer Id);
	
	public void deleteAllStudent();
	
	Student updateStudent(Student student,Integer id);

	List<Student> getStudentByClass(Integer StudClass);


}
