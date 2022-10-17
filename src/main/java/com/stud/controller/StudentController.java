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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StudentController {

@Autowired
IStudentService StudentService;

private Log log;

	public StudentController() {
		this.log=LogFactory.getLog((Class)this.getClass());
	}
	//ResponseEntity<Student> responseEntity=new ResponseEntity<>(HttpStatus.OK);
//	@PostMapping("/AddStudent")
//	Integer createStudent(@RequestBody Student student)
//	{	
//				String regno="";
//				ResponseEntity<Student> responseEntity=new ResponseEntity<>(HttpStatus.OK);
//				regno=StudentService.GenerateRegNo();
//				System.out.println(regno);
//				student.setStudentRegNo(regno);
//				student.setApplicationStatus("Pending");
//				Integer id =StudentService.saveStudent(student);
//				System.out.println(id);
//				log.info("id");
//				return id;				
//	}
	
	@PostMapping("/AddStudent")
	ResponseEntity<String> createStudent(@RequestBody Student student)
	{	
		ResponseEntity<String> responseEntity=new ResponseEntity<>(HttpStatus.OK);
		Integer id=0;
		String regno="";
		if(student.getStudentName()==null||student.getStudentName()=="")
		{
			responseEntity =new ResponseEntity<>("Please enter Student Name",HttpStatus.BAD_REQUEST);	
			return responseEntity;	
		}
		else if(student.getStudentName()!=null||student.getStudentName()!="")
		{
				boolean flag=StudentService.isStringOnlyAlphabet(student.getStudentName());
				if(!flag) {
				responseEntity =new ResponseEntity<>("Student Name Should contain Alphabet only!",HttpStatus.BAD_REQUEST);	
				return responseEntity;	
		}
				
		}
		else if(student.getAddress()==null||student.getAddress()=="")
		{
			responseEntity =new ResponseEntity<>("Please enter Address",HttpStatus.BAD_REQUEST);	
			return responseEntity;	
		}
		else if(student.getCity()==null||student.getCity()=="")
		{
			responseEntity =new ResponseEntity<>("Please enter City",HttpStatus.BAD_REQUEST);	
			return responseEntity;	
		}
		else if(student.getCountry()==null||student.getCountry()=="")
		{
			responseEntity =new ResponseEntity<>("Please enter country",HttpStatus.BAD_REQUEST);
			return responseEntity;	
		}
		else if(student.getParentName()==null||student.getParentName()=="") 
		{
			responseEntity =new ResponseEntity<>("Please enter ParentName",HttpStatus.BAD_REQUEST);
			return responseEntity;	
		}
		else if(student.getPrimContactPerson()==null||student.getPrimContactPerson()=="") 
		{
			responseEntity =new ResponseEntity<>("Please enter Primary ContactPerson",HttpStatus.BAD_REQUEST);
			return responseEntity;	
		}
		else if(student.getPrimContactPerson()!=null||student.getPrimContactPerson()!="")
		{
				boolean flag=StudentService.isStringOnlyAlphabet(student.getPrimContactPerson());
				if(!flag) {
				responseEntity =new ResponseEntity<>("Primary Contact Name Should contain Alphabet only!",HttpStatus.BAD_REQUEST);	
				return responseEntity;	
				}
		}
		else if(student.getPrimContactPersonMobile()==null||student.getPrimContactPersonMobile()=="") 
		{
			responseEntity =new ResponseEntity<>("Please enter PrimaryContactPerson Mobile",HttpStatus.BAD_REQUEST);
			return responseEntity;	
		}
		else if(student.getSecContactPerson()==null||student.getSecContactPerson()=="") 
		{
			responseEntity =new ResponseEntity<>("Please enter SecContactPerson",HttpStatus.BAD_REQUEST);
			return responseEntity;	
		}
		else if(student.getSecContactPerson()!=null||student.getSecContactPerson()!="")
		{
				boolean flag1=StudentService.isStringOnlyAlphabet(student.getPrimContactPerson());
				if(!flag1) {
				responseEntity =new ResponseEntity<>("Secondary Contact Name Should contain Alphabet only!",HttpStatus.BAD_REQUEST);	
				return responseEntity;	
				}
		}
		else if(student.getSecContactPersonMobile()==null||student.getSecContactPersonMobile()=="") 
		{
			responseEntity =new ResponseEntity<>("Please enter Mobile Number!",HttpStatus.BAD_REQUEST);
			return responseEntity;	
		}
		else if(student.getSecContactPersonMobile().length()!=10 ||student.getPrimContactPersonMobile().length()!=10)
		{
			responseEntity =new ResponseEntity<>("Invalid Mobile number!",HttpStatus.BAD_REQUEST);
			return responseEntity;	
		}
		else if(student.getZipCode().length()!=6)
		{
			responseEntity =new ResponseEntity<>("Invalid Zip Code!",HttpStatus.BAD_REQUEST);
			return responseEntity;	
		}
		else if(student.getAge()==null||student.getAge()=="")
		{
			responseEntity =new ResponseEntity<>("Enter Age",HttpStatus.BAD_REQUEST);
			return responseEntity;	
		}
		else if(student.getAge()!=null||student.getAge()!="")
		{
			int Age=Integer.parseInt(student.getAge());  
			if(Age<=4)
			{
			responseEntity =new ResponseEntity<>("Enter Age grater than 4",HttpStatus.BAD_REQUEST);
			return responseEntity;	
			}
		}else if(student.getRegDate()!=null||student.getRegDate()!="")
		{
			boolean flag=StudentService.CheckDate(student.getRegDate());
				
					if(!flag){
						responseEntity =new ResponseEntity<>("Regestaratin date in invalid",HttpStatus.BAD_REQUEST);
						return responseEntity;	
					}
		}
	
		
		try {
				
				regno=StudentService.GenerateRegNo();
				System.out.println(regno);
				student.setStudentRegNo(regno);
				student.setApplicationStatus("Pending");
				 id =StudentService.saveStudent(student);
				 responseEntity =new ResponseEntity<>("Record Created sucessfully Stud Id:"+regno,HttpStatus.OK);
				}catch (Exception e) {
				
				e.printStackTrace();
				responseEntity =new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				log.info("Record created sucessfully id:"+id);
				return responseEntity;				
	}
	

	@GetMapping("/getallStudent")
	List<Student> getAllStudent()
	{
		
		int size=StudentService.getAllStudent().size();
		log.info("************************"+size);
		return StudentService.getAllStudent();
	}
	
	@GetMapping("/getStudentById/{id}")
	public Optional<Student> getStudentById(@PathVariable Integer id)
	{
		log.info(id);
		Optional<Student> student=StudentService.getStudent(id);
		return student;
	}
	
//	@GetMapping("/getStudentById/{id}")
//	public Optional<Student> getStudentById(@PathVariable Integer id)
//	{
//		ResponseEntity<Student> responseEntity=new ResponseEntity<>(HttpStatus.OK);
//		Optional<Student> student = null;
//		try {
//			log.info(id);
//			student=StudentService.getStudent(id);	
//		
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			responseEntity =new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		return student;
//	}
	////**********************

	//test///

	@GetMapping("/hello")
	ResponseEntity<String> hello() {
	    return new ResponseEntity<>("Hello World!", HttpStatus.OK);
	}
//	@GetMapping("/getStudentByClass/{StudClass}")
//	public List<Student> getStudentByClass(@PathVariable Integer StudClass)
//	{
//		List<Student> student=StudentService.getStudentByClass(StudClass);
//				return student;
//	}
	
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
	public ResponseEntity<String> updateStudent(@PathVariable("id") Integer id,@RequestBody Student student)
	{	
		ResponseEntity<String> responseEntity=new ResponseEntity<>(HttpStatus.OK);
		
		if(student.getStudentName()==null||student.getStudentName()=="")
		{
			responseEntity =new ResponseEntity<>("Please enter Student Name",HttpStatus.BAD_REQUEST);	
			return responseEntity;	
		}
		else if(student.getStudentName()!=null||student.getStudentName()!="")
		{
				boolean flag=StudentService.isStringOnlyAlphabet(student.getStudentName());
				if(!flag) {
				responseEntity =new ResponseEntity<>("Student Name Should contain Alphabet only!",HttpStatus.BAD_REQUEST);	
				return responseEntity;	
		}
				
		}
		else if(student.getAddress()==null||student.getAddress()=="")
		{
			responseEntity =new ResponseEntity<>("Please enter Address",HttpStatus.BAD_REQUEST);	
			return responseEntity;	
		}
		else if(student.getCity()==null||student.getCity()=="")
		{
			responseEntity =new ResponseEntity<>("Please enter City",HttpStatus.BAD_REQUEST);	
			return responseEntity;	
		}
		else if(student.getCountry()==null||student.getCountry()=="")
		{
			responseEntity =new ResponseEntity<>("Please enter country",HttpStatus.BAD_REQUEST);
			return responseEntity;	
		}
		else if(student.getParentName()==null||student.getParentName()=="") 
		{
			responseEntity =new ResponseEntity<>("Please enter ParentName",HttpStatus.BAD_REQUEST);
			return responseEntity;	
		}
		else if(student.getPrimContactPerson()==null||student.getPrimContactPerson()=="") 
		{
			responseEntity =new ResponseEntity<>("Please enter Primary ContactPerson",HttpStatus.BAD_REQUEST);
			return responseEntity;	
		}
		else if(student.getPrimContactPerson()!=null||student.getPrimContactPerson()!="")
		{
				boolean flag=StudentService.isStringOnlyAlphabet(student.getPrimContactPerson());
				if(!flag) {
				responseEntity =new ResponseEntity<>("Primary Contact Name Should contain Alphabet only!",HttpStatus.BAD_REQUEST);	
				return responseEntity;	
				}
		}
		else if(student.getPrimContactPersonMobile()==null||student.getPrimContactPersonMobile()=="") 
		{
			responseEntity =new ResponseEntity<>("Please enter PrimaryContactPerson Mobile",HttpStatus.BAD_REQUEST);
			return responseEntity;	
		}
		else if(student.getSecContactPerson()==null||student.getSecContactPerson()=="") 
		{
			responseEntity =new ResponseEntity<>("Please enter SecContactPerson",HttpStatus.BAD_REQUEST);
			return responseEntity;	
		}
		else if(student.getSecContactPerson()!=null||student.getSecContactPerson()!="")
		{
				boolean flag1=StudentService.isStringOnlyAlphabet(student.getPrimContactPerson());
				if(!flag1) {
				responseEntity =new ResponseEntity<>("Secondary Contact Name Should contain Alphabet only!",HttpStatus.BAD_REQUEST);	
				return responseEntity;	
				}
		}
		else if(student.getSecContactPersonMobile()==null||student.getSecContactPersonMobile()=="") 
		{
			responseEntity =new ResponseEntity<>("Please enter Mobile Number!",HttpStatus.BAD_REQUEST);
			return responseEntity;	
		}
		else if(student.getSecContactPersonMobile().length()!=10 ||student.getPrimContactPersonMobile().length()!=10)
		{
			responseEntity =new ResponseEntity<>("Invalid Mobile number!",HttpStatus.BAD_REQUEST);
			return responseEntity;	
		}
		else if(student.getZipCode().length()!=6)
		{
			responseEntity =new ResponseEntity<>("Invalid Zip Code!",HttpStatus.BAD_REQUEST);
			return responseEntity;	
		}
		else if(student.getAge()==null||student.getAge()=="")
		{
			responseEntity =new ResponseEntity<>("Enter Age",HttpStatus.BAD_REQUEST);
			return responseEntity;	
		}
		else if(student.getAge()!=null||student.getAge()!="")
		{
			int Age=Integer.parseInt(student.getAge());  
			if(Age<=4)
			{
			responseEntity =new ResponseEntity<>("Enter Age grater than 4",HttpStatus.BAD_REQUEST);
			return responseEntity;	
			}
		}
		
		try {
			StudentService.updateStudent(student, id);
			return new ResponseEntity<String>(HttpStatus.OK);
		
			}catch (Exception e) {
			
			e.printStackTrace();
			responseEntity =new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return responseEntity;
	}
	
	@PutMapping("/updateStudentStatusById/{id}")
	public ResponseEntity<Student> updateStudentStatus(@PathVariable("id") Integer id,@RequestBody Student student)
	{
		return new ResponseEntity<Student>(StudentService.updateStudent(student, id),HttpStatus.OK);
	}
	
	
	
}
