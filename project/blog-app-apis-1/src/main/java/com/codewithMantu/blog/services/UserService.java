package com.codewithMantu.blog.services;

import java.util.List;

import com.codewithMantu.blog.payloads.UserDto;

public interface UserService {
	
	UserDto  createUser(UserDto userDto);
	
	UserDto updateUSer(UserDto userDto, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUser();
	
	void deleteUser(Integer userId);
	

}
