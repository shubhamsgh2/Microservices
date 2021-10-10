package com.restwebservices.restwebservices.users;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	@Autowired 
	private UserDAOService userDaoService;
	
	@GetMapping("/users")
	public List<User> retreiveAllUsers(){
		return userDaoService.findAll();
	}
	@GetMapping("/users/{id}")
	public User retreiveOneUsers(@PathVariable int id){
		User user= userDaoService.findOne(id);
		if(user==null) {
			throw new UserNotFoundException("id" + id);
			
		}
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user){
		User savedUser= userDaoService.save(user);
		
		//CREATED status 201 
		// /user/{id}   replace with savedUsser.getId()
		URI location=ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
	
		return ResponseEntity.created(location).build();
	
	}
	@DeleteMapping("/users/{id}")
	public void deleteID(@PathVariable int id){
		User user= userDaoService.deletebyId(id);
		if(user==null) {
			throw new UserNotFoundException("id" + id);
			
		}
		 
	}
	
	
}
