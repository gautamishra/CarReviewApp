package com.mindfire.carreview.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;

	private String email;

	private String name;

	private String password;

	private String  phone;

	private String image;
	
	@Column(name = "reset_token")
	private String resetToken;
	
	@Column(name="sign_up")
	private Boolean signUp;
	
//	@OneToMany(mappedBy="user" , fetch = FetchType.EAGER)
//	@JsonIgnore
//	private List<Roles> userRoles;
	
	

//	bi-directional many-to-one association to Commentss
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Commentss> commentsses;

//	bi-directional many-to-one association to Like
//	@OneToMany(mappedBy="user")
//	private List<Like> likes;

//	bi-directional many-to-one association to Review
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Review> reviews;

	public User() {
	}

	public String getResetToken() {
		return resetToken;
	}


	public void setResetToken(String token) {
		this.resetToken = token;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


//	public List<Roles> getUserRoles() {
//		return userRoles;
//	}
//
//	public void setUserRoles(List<Roles> userRoles) {
//		this.userRoles = userRoles;
//	}
//
//	public Roles addUserRole(Roles userRole) {
//		getUserRoles().add(userRole);
//		userRole.setUser(this);
//
//		return userRole;
//	}
//
//	public Roles removeUserRole(Roles userRole) {
//		getUserRoles().remove(userRole);
//		userRole.setUser(null);
//
//		return userRole;
//	}
	
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getSignUp() {
		return this.signUp;
	}

	public void setSignUp(Boolean signUp) {
		this.signUp = signUp;
	}

	public List<Commentss> getCommentsses() {
		return this.commentsses;
	}

	public void setCommentsses(List<Commentss> commentsses) {
		this.commentsses = commentsses;
	}

	public Commentss addCommentss(Commentss commentss) {
		getCommentsses().add(commentss);
		commentss.setUser(this);

		return commentss;
	}

	public Commentss removeCommentss(Commentss commentss) {
		getCommentsses().remove(commentss);
		commentss.setUser(null);

		return commentss;
	}

//	public List<Like> getLikes() {
//		return this.likes;
//	}
//
//	public void setLikes(List<Like> likes) {
//		this.likes = likes;
//	}
//
//	public Like addLike(Like like) {
//		getLikes().add(like);
//		like.setUser(this);
//
//		return like;
//	}

//	public Like removeLike(Like like) {
//		getLikes().remove(like);
//		like.setUser(null);
//
//		return like;
//	}

	public List<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Review addReview(Review review) {
		getReviews().add(review);
		review.setUser(this);

		return review;
	}

	public Review removeReview(Review review) {
		getReviews().remove(review);
		review.setUser(null);
		return review;
	}

}