package com.hibernatejpa.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernatejpa.jpa.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{

}
