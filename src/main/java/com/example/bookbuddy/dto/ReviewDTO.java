package com.example.bookbuddy.dto;

import java.time.LocalDateTime;

public class ReviewDTO {

	private String text;
	private LocalDateTime daterev;
	private int idBook;
	private int idUser;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getIdBook() {
		return idBook;
	}

	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public LocalDateTime getDaterev() {
		return daterev;
	}

	public void setDaterev(LocalDateTime daterev) {
		this.daterev = daterev;
	}
	
	

}
