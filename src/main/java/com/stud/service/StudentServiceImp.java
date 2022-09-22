package com.stud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stud.exception.ResourceNotFoundException;
import com.stud.model.Student;

@Service
public class StudentServiceImp implements IStudentService {

	
	@Autowired
	IStudentRepo StudentRepo;

	@Override
	public Integer saveStudent(Student student) {
	Student saveStud = StudentRepo.save(student);
	return saveStud.getId();
	}

	@Override
	public List<Student> getAllStudent() {
		return StudentRepo.findAll();
	}

	@Override
	public Optional<Student> getStudent(Integer Id) {
		return StudentRepo.findById(Id);

	}
	
	@Override
	public void deleteStudent(Integer Id) {
		StudentRepo.deleteById(Id);
		
	}

	@Override
	public void deleteAllStudent() {
		
		StudentRepo.deleteAll();		
	}

	@Override
	public Student updateStudent(Student student, Integer id) {
		Student existingStudent= StudentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student", "id", id));
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setDivision(student.getDivision());
		existingStudent.setDob(student.getDob());
		existingStudent.setPhoneNumber(student.getPhoneNumber());
		existingStudent.setRollNo(student.getRollNo());
		existingStudent.setStudClass(student.getStudClass());
		StudentRepo.save(existingStudent);	
		return existingStudent;
	}

	@Override
	public List<Student>getStudentByClass(Integer StudClass) {
			
		return StudentRepo.findByStudClass(StudClass);
	}
	
}
