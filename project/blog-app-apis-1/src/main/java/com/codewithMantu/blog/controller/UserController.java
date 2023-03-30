package com.codewithMantu.blog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithMantu.blog.payloads.UserDto;
import com.codewithMantu.blog.services.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	//Post-create user
	@PostMapping("/")
	public ResponseEntity<UserDto>createUser(@RequestBody UserDto userDto){
		UserDto createUser = this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createUser,HttpStatus.CREATED);
		
	}
	
	//put update user
	@PutMapping("{userId}")
	public ResponseEntity<UserDto>updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer userId){
		UserDto updateUSer = this.userService.updateUSer(userDto, userId);
		
		return ResponseEntity.ok(updateUSer);
		
	}
	
	//Get user get
	@GetMapping("{userId}")
	public ResponseEntity<UserDto>getuserById(@PathVariable("userId") Integer userId){
		//UserDto userById = this.userService.getUserById(userId);
		
		
//		return new ResponseEntity<UserDto>(userById,HttpStatus.OK);
		return  ResponseEntity.ok(this.userService.getUserById(userId));
	
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>>getAllUsers(){
    	
    	return  ResponseEntity.ok(this.userService.getAllUser());
    		
    }
	
	
	
	 @DeleteMapping("{userid}")
	public ResponseEntity<UserDto>deleleUser(@PathVariable("userId") Integer userId){
		  this.userService.deleteUser(userId);
		return new ResponseEntity(Map.of("message"," User Delete successfully"), HttpStatus.OK);
		 
	 }
	
	
	
	
	
	

	

}
