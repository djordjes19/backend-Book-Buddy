package com.example.bookbuddy.repositories;

import java.util.List;

import com.example.bookbuddy.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Integer> {

	Book findByTitle(String title);
	
	Book findBookByIdBook(int num);
	
	List<Book> findByAuthor(String author);
}
