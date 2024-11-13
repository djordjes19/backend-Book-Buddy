package com.example.bookbuddy.repositories;

import com.example.bookbuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

	
	
	User findByUsername(String name);
//	User findByIdUser(Integer idUser);
}
