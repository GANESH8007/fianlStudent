package com.stud.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stud.exception.ResourceNotFoundException;

//import com.stud.model.State;
import com.stud.model.Student;


@Service
public class StudentServiceImp implements IStudentService {

	private Log log;

	public StudentServiceImp() {
		this.log=LogFactory.getLog((Class)this.getClass());
	}
	
	@Autowired
	IStudentRepo StudentRepo;
	

	@Override
	public Integer saveStudent(Student student) {

	Student saveStud = StudentRepo.save(student);
	log.info("student record no:"+saveStud.getId());
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
	public String GenerateRegNo() {
		
		Random r=new Random();
		int num=r.nextInt(999);
		//String result="R-"+String.format("%3d",num);
		return "R-"+String.format("%3d",num);		
	}

	@Override
	public Student updateStudent(Student student, Integer id) {
		Student existingStudent= StudentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student", "id", id));
			
		existingStudent.setParentName(student.getParentName());
		existingStudent.setStudentName(student.getStudentName());
		existingStudent.setStudentRegNo(student.getStudentRegNo());
		existingStudent.setAddress(student.getAddress());
		existingStudent.setState(student.getState());
		existingStudent.setCountry(student.getCountry());
		existingStudent.setCity(student.getCity());
		existingStudent.setZipCode(student.getZipCode());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setPrimContactPerson(student.getPrimContactPerson());
		existingStudent.setPrimContactPersonMobile(student.getPrimContactPersonMobile());
		existingStudent.setSecContactPerson(student.getSecContactPerson());
		existingStudent.setSecContactPersonMobile(student.getSecContactPersonMobile());
		existingStudent.setApplicationStatus(student.getApplicationStatus());
		existingStudent.setAge(student.getAge());
		existingStudent.setRegDate(student.getRegDate());

		
		StudentRepo.save(existingStudent);	
		return existingStudent;
	}
	
	@Override
	public Student updateStudentStatus(Student student, Integer id) {
		Student existingStudent= StudentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student", "id", id));
		existingStudent.setApplicationStatus(student.getApplicationStatus());
		StudentRepo.save(existingStudent);	
		return existingStudent;
	}

	@Override
	public boolean isStringOnlyAlphabet(String str)
    {
 
        return ((str != null) && (!str.equals(""))
                && (str.matches("^[a-zA-Z]*$")));
    }

	@Override
	public  boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}

	@Override
	public  boolean CheckDate(String givenDate) {
	 SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
     try {
    	LocalDate ld =java.time.LocalDate.now();
    	Date d1 = sdformat.parse(givenDate);
		Date d2 =sdformat.parse(ld.toString());
		if(d1.compareTo(d2) >= 0)
		{
			return true;
		}
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     return false;
	}
//	@Override
//	public List<State> getCity(String state) {
//		
//		return StateRepo.findCities(state);
//	}


	

//	@Override
//	public List<Student>getStudentByClass(Integer StudClass) {
//			
//		return StudentRepo.findByStudClass(StudClass);
//	}
	
}
