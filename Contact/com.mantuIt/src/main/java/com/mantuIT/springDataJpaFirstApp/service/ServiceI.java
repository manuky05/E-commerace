package com.mantuIT.springDataJpaFirstApp.service;

import java.util.List;

import com.mantuIT.springDataJpaFirstApp.model.User;



public interface ServiceI {
	
	public void  saveUser(User user);
	
	public void saveAllUser(List<User> list);
	
	public User getUserById(Integer userId);
	
	public  List<User>  getAllId(List<Integer> list);
	
	public List<User> getAllUser();

}
