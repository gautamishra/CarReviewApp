package com.mindfire.carreview.dto;

/**
 * DTO Class For User
 * @author mindfire
 *
 */
public class UserDTO {
	
	private String email;

	private String name;

	private String password;

	private String  phone;
	
	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}
