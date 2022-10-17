package com.stud.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {
	
	private Integer id;
	private String parentName;
	private String studentName;
	private String studentRegNo;
	private String address;
	private String state;
	private String country;
	private String city;
	private String zipCode;
	private String email;
	private String primContactPerson;
	private String primContactPersonMobile;
	private String secContactPerson;
	private String secContactPersonMobile;
	private String applicationStatus;
	private String age;
	private String regDate;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentRegNo() {
		return studentRegNo;
	}
	public void setStudentRegNo(String studentRegNo) {
		this.studentRegNo = studentRegNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPrimContactPerson() {
		return primContactPerson;
	}
	public void setPrimContactPerson(String primContactPerson) {
		this.primContactPerson = primContactPerson;
	}
	public String getPrimContactPersonMobile() {
		return primContactPersonMobile;
	}
	public void setPrimContactPersonMobile(String primContactPersonMobile) {
		this.primContactPersonMobile = primContactPersonMobile;
	}
	public String getSecContactPerson() {
		return secContactPerson;
	}
	public void setSecContactPerson(String secContactPerson) {
		this.secContactPerson = secContactPerson;
	}
	public String getSecContactPersonMobile() {
		return secContactPersonMobile;
	}
	public void setSecContactPersonMobile(String secContactPersonMobile) {
		this.secContactPersonMobile = secContactPersonMobile;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	
	public Student() {
		super();
	}
	public Student(Integer id, String parentName, String studentName, String studentRegNo, String address, String state,
			String country, String city, String zipCode, String email, String primContactPerson,
			String primContactPersonMobile, String secContactPerson, String secContactPersonMobile,
			String applicationStatus, String age, String regDate) {
		super();
		this.id = id;
		this.parentName = parentName;
		this.studentName = studentName;
		this.studentRegNo = studentRegNo;
		this.address = address;
		this.state = state;
		this.country = country;
		this.city = city;
		this.zipCode = zipCode;
		this.email = email;
		this.primContactPerson = primContactPerson;
		this.primContactPersonMobile = primContactPersonMobile;
		this.secContactPerson = secContactPerson;
		this.secContactPersonMobile = secContactPersonMobile;
		this.applicationStatus = applicationStatus;
		this.age = age;
		this.regDate = regDate;
	}



	
}
