package com.example.demo.dto;

public class UserDTO {

	private String name;
	private String username;
	private String email;
	private int idUserType;
	private int idUser;
	

	public UserDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "name=" + name;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public int getIdUserType() {
		return idUserType;
	}

	public void setIdUserType(int idUserType) {
		this.idUserType = idUserType;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	
}
