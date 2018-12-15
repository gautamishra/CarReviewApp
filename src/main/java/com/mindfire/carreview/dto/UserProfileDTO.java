package com.mindfire.carreview.dto;

import java.util.List;

import com.mindfire.carreview.model.Review;

public class UserProfileDTO {
	
	private String email;

	private String name;

	private String phone;
	
	private String image;
	
	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	private List<Review> review;

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public List<Review> getReview() {
		return review;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

}
