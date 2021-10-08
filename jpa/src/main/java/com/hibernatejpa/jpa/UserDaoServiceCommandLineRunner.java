package com.hibernatejpa.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hibernatejpa.jpa.entity.User;
import com.hibernatejpa.jpa.service.UserDAOServoce;
@Component
public class UserDaoServiceCommandLineRunner implements CommandLineRunner {

	@Autowired
	private UserDAOServoce userDaoService;
	
	private static final Logger logger=LoggerFactory.getLogger(UserDaoServiceCommandLineRunner.class);
	
	@Override
	public void run(String... args) throws Exception {
		User user=new User("Jack","Admin");
		long insert=userDaoService.insert(user);
		logger.info("New user is created"+ user);
		
				
	}

}
