package com.p.model;

import java.io.Serializable;

public class UserTest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String username;
	private String email;
	
	
	public UserTest() {
		super();
	}
	public UserTest(String username, String email) {
		super();
		this.username = username;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
