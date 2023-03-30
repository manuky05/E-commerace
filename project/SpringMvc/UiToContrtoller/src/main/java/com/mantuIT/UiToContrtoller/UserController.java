package com.mantuIT.UiToContrtoller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mantuIT.UiToContrtoller.controller.User;



@Controller
public class UserController {
	@GetMapping("/loadform")
	public String loadform() {
		
	
		return "userRegi";
		
	}
	@PostMapping("/saveUser")
	public String saveUser ( User user,Model model) {
		
		System.out.println(user);
		
		model.addAttribute("USER",user);
		
		return "userSuccess";
		
		
	}
}

