package com.mantuIT.RestApplication.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mantuIT.RestApplication.entity.Controy;
import com.mantuIT.RestApplication.service.ControyServiceImpl;
@SpringBootTest(classes=(ControyControllerTest.class))
class ControyControllerTest {
	
    
	@Mock
	private ControyServiceImpl service;
	
	@InjectMocks
	private ControyController controy;
	
	@Test
	public void testGetAllContries() {
		
		List<Controy> mycontroy=new  ArrayList<Controy>(); 
		
		mycontroy.add(new Controy(1,"India","Surat"));
		mycontroy.add(new Controy(2,"India","Patna"));
		
		when(service.getAllContries()).thenReturn(mycontroy);
		
		  ResponseEntity<List<Controy>> allContries = controy.getAllContries();
		
		assertEquals(HttpStatus.FOUND,allContries.getStatusCode() );
		assertEquals(2, allContries.getBody().size());
		
	}
@Test
	public void testfindControyById() {
		Controy mycontroy=new Controy(1,"India","patan");
		
		
	;
		
		int controyId=1;
		
		when(service.getcontroyById(controyId)).thenReturn(mycontroy);
		
		ResponseEntity<Controy> controyById = controy.getControyById(controyId);
		
		
		assertEquals(HttpStatus.FOUND, controyById.getStatusCode());
		assertEquals(1, controyById.getBody().getId());
		
		
		
		
		
	}
     @Test
     public void testgetControyByName() {
    	 
    	 Controy mycontroy=new Controy(1, "India", "Delhi");
    	 String getcontroyByname="India";
    	 
    	 when(service.getControyByName(getcontroyByname)).thenReturn(mycontroy);
    	 
    	 ResponseEntity<Controy> controyByName = controy.getControyByName(getcontroyByname);
    	 
    	 assertEquals(HttpStatus.FOUND, controyByName.getStatusCode());
    	 assertEquals(getcontroyByname, controyByName.getBody().getControyname());
    	 
    	 
     }
}
