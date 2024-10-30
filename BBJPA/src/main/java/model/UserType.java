package model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


/**
 * The persistent class for the user_type database table.
 * 
 */
@Entity
@Table(name="user_type")
@NamedQuery(name="UserType.findAll", query="SELECT u FROM UserType u")
public class UserType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="iduser_type")
	private int iduserType;

	@Column(name="name")
	private String name;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="userType")
	@JsonIgnore
	private List<User> users;

	public UserType() {
	}

	public int getIduserType() {
		return this.iduserType;
	}

	public void setIduserType(int iduserType) {
		this.iduserType = iduserType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	@Override
	public String toString() {
	    return "UserType [iduserType=" + iduserType + ", name=" + name + "]";
	}

	

}
