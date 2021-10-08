package com.hibernatejpa.jpa;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hibernatejpa.jpa.entity.User;
import com.hibernatejpa.jpa.service.UserDAOServoce;
import com.hibernatejpa.jpa.service.UserRepository;
@Component
public class UserRepositoryCommandLineRunner2 implements CommandLineRunner {

	@Autowired
	private UserRepository userDaoService;
	
	private static final Logger logger=LoggerFactory.getLogger(UserRepositoryCommandLineRunner2.class);
	
	@Override
	public void run(String... args) throws Exception {
		User user=new User("Jill","Admin");
		 userDaoService.save(user);
		logger.info("New user is created"+ user);
		Optional<User> userwithIDOne= userDaoService.findById(1L);
		logger.info("User is retreived with id=1"+ userwithIDOne);
		
		List<User> users=userDaoService.findAll();
		logger.info("All Users: "+ users);
		
		
		
				
	}

}
