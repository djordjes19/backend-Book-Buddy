package com.example.bookbuddy.repositories;

import com.example.bookbuddy.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Integer> {

	 Review findById(int id);
}
