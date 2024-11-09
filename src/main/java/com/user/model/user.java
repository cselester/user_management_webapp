package com.user.model;

public class user {

	
	
	private String name;
	private String email;
	private String country;
	private String password;
	

	public user() {
		
	}


	public user(String name, String email, String country, String password) {
		super();
		
		this.name = name;
		this.email = email;
		this.country = country;
		this.password = password;
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


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "user [name=" + name + ", email=" + email + ", country=" + country + ", password=" + password + "]";
	}
	
	
	
}
