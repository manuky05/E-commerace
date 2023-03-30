package com.BikkadIT.model.JavaToJsonObject;

import java.io.File;
import java.io.IOException;

import com.BikkadIT.model.Customer;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToJavaObject {
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		
		
		ObjectMapper objectMapper=new ObjectMapper();
		
		
		Customer value=objectMapper.readValue(new File("Customer.json"), Customer.class);
		System.out.println(value);
	}

}

