package com.BikkadIT.QueryParamAndPathParam.controler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	@GetMapping("/welcome")
	public ResponseEntity<String> welcomeMsg(@RequestParam String name) {
		
		String msg="Hii" +name+ " Welcome to Mantu For devolopment";
		System.out.println(msg);
		
		return new ResponseEntity<String> (msg,HttpStatus.OK);
		
	}

}
