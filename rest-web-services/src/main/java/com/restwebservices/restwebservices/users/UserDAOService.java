package com.restwebservices.restwebservices.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDAOService {
	private static List<User> users= new ArrayList<>();
	private static int userCount=3;
	
	static {
		users.add(new User(1,"Jack",new Date()));
		users.add(new User(2,"Shubham",new Date()));
		users.add(new User(1,"Jill",new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user){
		if(user.getId()== null ) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User deletebyId(int id) {
		Iterator<User> itr=users.iterator();
		while(itr.hasNext()) {
			User user=itr.next();
			if(user.getId()==id) {
				itr.remove();
				return user;
			}
		}
		return null;
		
	}
	public User findOne( int id){
		for(User user: users) {
			if(user.getId()== id ) {
				return user;	
		}
	}
		return null;
	}}
