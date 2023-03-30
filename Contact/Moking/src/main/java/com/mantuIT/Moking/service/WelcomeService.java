package com.mantuIT.Moking.service;
//mack ten test method
import org.springframework.web.bind.annotation.GetMapping;

public class WelcomeService {
	
	@GetMapping("/welcome")
	public String welcomemsg() {
		
		return "welcomemsg to BikkadIT";
		
	}

}
