package com.codewithMantu.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithMantu.blog.entities.User;
import com.codewithMantu.blog.exceptions.ResourceNotFoundException;
import com.codewithMantu.blog.payloads.UserDto;
import com.codewithMantu.blog.repositories.UserRepo;
import com.codewithMantu.blog.services.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.DtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUSer(UserDto userDto, Integer userId) {
	User user=this.userRepo.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));
	
	user.setName(userDto.getName());
	user.setEmail(userDto.getEmail());
	user.setPassword(userDto.getPassword());
	user.setAbout(userDto.getAbout());
	
	User updatedUser=this.userRepo.save(user);
	UserDto userDto1=this.updateUSer(userDto, userId);
	
		return userDto1 ;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user ->this.userToDto(user)).Collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		this.userRepo.delete(user);
		
	}
	public User DtoToUser(UserDto userDto) {
		User user=new User();
		
	user.setId(userDto.getId());
	user.setName(userDto.getName());
	user.setPassword(userDto.getPassword());
	user.setAbout(userDto.getAbout());
	return user;
		
	}
	 UserDto userToDto(User user) {
		
		UserDto userDto=new UserDto();
		
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setAbout(user.getAbout());
		
		
		
		return userDto;
		
	}

}
