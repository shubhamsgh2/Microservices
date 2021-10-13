package com.restwebservices.restwebservices.users;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	@Id
	@GeneratedValue
 private Integer id;
	
 private String description;
 
 @ManyToOne(fetch=FetchType.LAZY)
 @JsonIgnore 
 private User user;


public Post(Integer id, String description) {
	super();
	this.id = id;
	this.description = description;
}


public Post() {
	super();
}


public Integer getId() {
	return id;
}


public String getDescription() {
	return description;
}


public User getUser() {
	return user;
}


public void setId(Integer id) {
	this.id = id;
}


public void setDescription(String description) {
	this.description = description;
}


public void setUser(User user) {
	this.user = user;
}
 
 
}
