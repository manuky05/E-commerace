package com.mantuIT.RestApplication.controller;

import static org.junit.jupiter.api.Assertions.*;
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
@SpringBootTest(classes= {ControyControllerTest4.class})
class ControyControllerTest4 {

	@Mock
	private ControyServiceImpl service;
	@InjectMocks
	private ControyController controy;
	
	
	@Test
	public void testControyController() {
		
		List<Controy> mycontroy=new ArrayList<Controy>();
		
		mycontroy.add(new Controy(1,"India","patana"));
		mycontroy.add(new Controy(2,"India","Surat"));
		
		when(service.getAllContries()).thenReturn(mycontroy);
		ResponseEntity<List<Controy>> allContries = controy.getAllContries();
		
		assertEquals(HttpStatus.FOUND, allContries.getStatusCode());
		
		assertEquals(2, allContries.getBody().size());
		
		
		
		
	}

}
