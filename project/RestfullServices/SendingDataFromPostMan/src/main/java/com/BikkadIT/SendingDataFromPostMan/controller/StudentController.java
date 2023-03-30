package com.BikkadIT.SendingDataFromPostMan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.SendingDataFromPostMan.model.Student;
import com.BikkadIT.SendingDataFromPostMan.service.StudentServiceI;

@RestController
public class StudentController {
	
	@Autowired
	private StudentServiceI studentServiceI;
	@PostMapping(value ="/saveStudent", consumes="application/json",produces="application/json")
	public ResponseEntity<Student> saveStudent(@RequestBody Student stu){
		
		
		System.out.println(stu);
		Student student=studentServiceI.saveStudent(stu);
		System.out.println(student);
		return new ResponseEntity<Student>(student, HttpStatus.CREATED);
		
	}

}
