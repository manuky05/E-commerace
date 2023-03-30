package com.BikkadIT.JsonToJava;

import java.io.File;
import java.io.IOException;

import com.BikkadIT.binding.Student;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToJavaObject {
	
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		
		File file=new File("Student.json");
		ObjectMapper objectMapper=new ObjectMapper();
		Student stu = objectMapper.readValue(file, Student.class); 
		System.out.println(stu);
	}

}
