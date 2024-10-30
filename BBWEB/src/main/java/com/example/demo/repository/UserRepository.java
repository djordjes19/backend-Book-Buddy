package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	
	
	User findByUsername(String name);
//	User findByIdUser(Integer idUser);
}
