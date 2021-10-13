package com.restwebservices.restwebservices.users;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.hateoas.EntityModel;
import  org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
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
public class UserJPAResource {
	@Autowired 
	private UserDAOService userDaoService;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private PostRepository postRepository;

	
	@GetMapping("jpa/users")
	public List<User> retreiveAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundException("id-" + id);

		// "all-users", SERVER_PATH + "/users"
		// retrieveAllUsers
		EntityModel<User> resource = EntityModel.of(user.get());//new EntityModel<User>(user.get());

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retreiveAllUsers());

		resource.add(linkTo.withRel("all-users"));

		// HATEOAS

		return resource;
		/*
		 * EntityModel<User> model=EntityModel.of(user);
		 * 
		 * WebMvcLinkBuilder linkToUsers= linkTo( methodOn(this.getClass())
		 * .retreiveAllUsers() ); model.add(linkToUsers.withRel("all-users")); return
		 * model;
		 */
		 
	}
	
	@PostMapping("jpa/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user){
		User savedUser= userRepository.save(user);
		
		//CREATED status 201 
		// /user/{id}   replace with savedUsser.getId()
		URI location=ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
	
		return ResponseEntity.created(location).build();
	
	}
	@DeleteMapping("jpa/users/{id}")
	public void deleteID(@PathVariable int id){
		  userRepository.deleteById(id);
		 
		 
	}
	
	
	@GetMapping("jpa/users/{id}/posts")
	public List<Post> retreivePostByUser(@PathVariable int id){
		 Optional<User> userOptional= userRepository.findById(id);
		
		 if (!userOptional.isPresent())
				throw new UserNotFoundException("id-" + id);

		 
		 return userOptional.get().getPosts();
	
	
	}
	
	@PostMapping("jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post){
		
		
		Optional<User> userOptional= userRepository.findById(id);
		
		 if (!userOptional.isPresent())
				throw new UserNotFoundException("id-" + id);
	 
		User  user=userOptional.get();
	
		post.setUser(user);
		postRepository.save(post);
		
		
		//CREATED status 201 
		// /user/{id}   replace with savedUsser.getId()
		URI location=ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(post.getId()).toUri();
	
		return ResponseEntity.created(location).build();
	
	}
	
}
