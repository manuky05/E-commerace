package com.mantuIT.springDataJpaFirstApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.mantuIT.springDataJpaFirstApp.model.User;
import com.mantuIT.springDataJpaFirstApp.service.ServiceImpl;

@SpringBootApplication
public class SprigDataJpaFirstAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SprigDataJpaFirstAppApplication.class, args);
	
		ServiceImpl bean = context.getBean(ServiceImpl.class);
		//save
//		User user=new User();
//		user.setUserid(1);
//		user.setUserNname("mantu");
//		user.setUseraddress("Nashik");
//		user.setUserage(24);
//		
//		bean.saveUser(user);
		
		
	//saveAll();
		
//		User user=new User();
//		user.setUserId(4);
//		user.setUserName("dgkd");
//		user.setUseraddress("Aurangabad");
//		user.setUserAge(32);
//		
//		User user1=new User();
//		user1.setUserId(5);
//		user1.setUserName("kjfhgkjh");
//		user1.setUseraddress("jgjg");
//		user1.setUserAge(32);
//		
//		
//		User user2=new User();
//		user2.setUserId(6);
//		user2.setUserName("kdjhd");
//		user2.setUseraddress("dggj");
//		user2.setUserAge(32);
//		
//		List l=new ArrayList();
//		l.add(user);
//		l.add(user1);
//		l.add(user2);
//		
//		bean.saveAllUser(l);
//	
		
		
//		User user = bean.getUserById(1);
//		System.out.println(user);
//		
		
//		List<Integer> asList = Arrays.asList(1,3,5);
//		List<User> allId = bean.getAllId(asList);
//		System.out.println(allId);
//		
//		for(User usr:allId) {
//			System.out.println(usr);
//		}
		
//		List<User> allUser = bean.getAllUser();
//		System.out.println(allUser);
//		
//		for(User usr:allUser) {
//			System.out.println(usr);
//		}
		
		
	}
	}