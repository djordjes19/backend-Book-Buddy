package com.example.bookbuddy.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;

/**
 * The persistent class for the review database table.
 * 
 */
@Entity
@NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r")
public class Review implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReview;

	@Column(name = "text", nullable = false)
	private String text;
	
	@Column(name="daterev")
	private LocalDateTime daterev;

	// bi-directional many-to-one association to Book
	@ManyToOne
	@JoinColumn(name = "book_idBook", nullable = false)
	@JsonIgnore
	private Book book;

	@ManyToOne
	@JoinColumn(name = "user_idUser", nullable = false)
	@JsonIgnore
	private User user;

	public Review() {
	}

	public int getIdReview() {
		return this.idReview;
	}

	public void setIdReview(int idReview) {
		this.idReview = idReview;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getDaterev() {
		return daterev;
	}

	public void setDaterev(LocalDateTime daterev) {
		this.daterev = daterev;
	}
	
	

}