package com.dropbeatzadmin.model;

public class UserModel {
	
	private int id;
	private String fullname;
	private String username;
	private String country;
	private String contactnumber;
	private String password;
	private String email;
	private String role;
	
	public UserModel(int id, String fullname, String username, String country, String contactnumber, String password,
			String email, String role) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.username = username;
		this.country = country;
		this.contactnumber = contactnumber;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public String getFullname() {
		return fullname;
	}

	public String getUsername() {
		return username;
	}

	public String getCountry() {
		return country;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getRole() {
		return role;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	

}



