package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	Book findByTitle(String title);
	
	Book findBookByIdBook(int num);
	
	List<Book> findByAuthor(String author);
}
