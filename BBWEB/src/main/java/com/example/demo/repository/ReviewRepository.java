package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

	 Review findById(int id);
}
