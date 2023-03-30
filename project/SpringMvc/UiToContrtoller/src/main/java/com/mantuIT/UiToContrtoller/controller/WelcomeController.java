package com.mantuIT.UiToContrtoller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class WelcomeController {
	
	@GetMapping("/welcome")
	public String welcomeMsg(@RequestParam String name, Model model) {
		
		
		String msg= "hi " +name+ " welcome to mantu";
		
		System.out.println(msg);
		model.addAttribute ("MSG",msg);
		
		return "welcome";
		
		
	}

}
