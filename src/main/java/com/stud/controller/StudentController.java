package com.stud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stud.model.Student;
import com.stud.service.IStudentService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StudentController {
	
@Autowired
IStudentService StudentService;

	@PostMapping("/AddStudent")
	Integer createStudent(@RequestBody Student student)
	{
		Integer id =StudentService.saveStudent(student);
		System.out.println(id);
		return id;
	}

	@GetMapping("/getallStudent")
	List<Student> getAllStudent()
	{
		return StudentService.getAllStudent();
	}
	
	@GetMapping("/getStudentById/{id}")
	public Optional<Student> getStudentById(@PathVariable Integer id)
	{
		Optional<Student> student=StudentService.getStudent(id);
				return student;
	}

	
	@GetMapping("/getStudentByClass/{StudClass}")
	public List<Student> getStudentByClass(@PathVariable Integer StudClass)
	{
		List<Student> student=StudentService.getStudentByClass(StudClass);
				return student;
	}
	
	@DeleteMapping("/DeleteStudentById/{id}")
	public ResponseEntity<Student> DeleteStudentById(@PathVariable Integer id)
	{
		System.out.println(id);
		ResponseEntity<Student> responseEntity=new ResponseEntity<>(HttpStatus.OK);
		try{
			StudentService.deleteStudent(id);
		}catch (Exception e) {
			e.printStackTrace();
			responseEntity =new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	@DeleteMapping("/DeleteAllStudent")
	public void deleteStudent()
	{
		StudentService.deleteAllStudent();
	}

	@PutMapping("/updateStudentById/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") Integer id,@RequestBody Student student)
	{
		return new ResponseEntity<Student>(StudentService.updateStudent(student, id),HttpStatus.OK);
	}
	

}
