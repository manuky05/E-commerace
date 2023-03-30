package com.BikkadIT.SpringDataJpaFirstApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BikkadIT.SpringDataJpaFirstApp.User.repository.UserRepository;
import com.BikkadIT.SpringDataJpaFirstApp.model.User;

@Service
public class serviceImpl implements ServiceI {
	@Autowired
	private UserRepository userRepository;
	public Object getAlluser;

	@Override
	public void saveUser(User user) {
		
		userRepository.save(user);
			
	}

	@Override
	public void saveAllUser(List<User> list) {
	Iterable<User> saveAll = userRepository.saveAll(list);
	
		
	}

	@Override
	public User getUserById(Integer userId) {
		User user = userRepository.findById(userId).get();
		return user ;
	}

	@Override
	public List<User> getAllId(List<Integer> list) {
		Iterable<User> findAllById = userRepository.findAllById(list);
		return (List<User>)findAllById;
	}

	@Override
	public List<User> getAllUser() {
		List<User> findAll = (List<User>) userRepository.findAll();
		return findAll;
	}

}
