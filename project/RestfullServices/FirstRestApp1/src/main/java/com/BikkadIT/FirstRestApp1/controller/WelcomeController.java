package com.BikkadIT.FirstRestApp1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcomeController")
public class WelcomeController {
	@GetMapping("/Welcome")
	public String WelcomeMsg() {
		
		String msg="welcome to Bikkad It";
		return msg;
	}
@GetMapping("/WelcomeCity")
	public String WelcomeCity() {
		String msg = "Welcome City to Pune";
		return msg;
		
		
	}
}
