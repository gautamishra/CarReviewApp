package com.mindfire.carreview.dto;

/**
 * DTO For Comment 
 * @author mindfire
 *
 */
public class CommentssDTO {
	
	private Integer userId;
	private Integer reviewId;
	private Integer parentId;
	private String comment;
	public Integer getUserId() {
		return userId;
	}
	public Integer getReviewId() {
		return reviewId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public String getComment() {
		return comment;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}