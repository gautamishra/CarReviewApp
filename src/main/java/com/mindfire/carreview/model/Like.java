package com.mindfire.carreview.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the likes database table.
 * 
 */
@Entity
@Table(name="likes")
public class Like implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="like_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer likeId;

	//bi-directional many-to-one association to Review
	@ManyToOne
	@JoinColumn(name="review_id")
	private Review review;

	//bi-directional many-to-one association to User
//	@ManyToOne
//	@JoinColumn(name="user_id")
//	private User user;

	public Like() {
	}

	public Integer getLikeId() {
		return this.likeId;
	}

	public void setLikeId(Integer likeId) {
		this.likeId = likeId;
	}

	public Review getReview() {
		return this.review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

//	public User getUser() {
//		return this.user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

}