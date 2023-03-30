package com.Mantu.aadharService.controller;

import java.util.HashMap;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.Mantu.aadharService.bean.Adhar;

@RestController
public class AdharController {

	@GetMapping("/getaadharcard/{id}")
	public Adhar getAadhar(@PathVariable Integer id) {
		
		
		HashMap<String, Integer>  urivariable=new HashMap<>();
		
		urivariable.put("id", id);
		ResponseEntity<Adhar> response= new RestTemplate()
				.getForEntity("https://localhost:9090/api/getuser/{id}",Adhar.class,urivariable);
		Adhar adhar=response.getBody();
		Random random=new Random();
		Integer adharnumber=random.nextInt();
		Adhar data=new Adhar(
				adharnumber,
				id,
				adhar.getFirstName(),
				adhar.getLastName(),
				adhar.getEmail(),
				adhar.getPassword(),
				adhar.getAbout(), 
				adhar.getRoles());
		
		return data;
		
	}
}
