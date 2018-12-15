package com.mindfire.carreview.dto;

public class ResetPasswordDTO {
	private Integer userId;
	private String password;
	private String token;
	public Integer getUserId() {
		return userId;
	}
	public String getPassword() {
		return password;
	}
	public String getToken() {
		return token;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
