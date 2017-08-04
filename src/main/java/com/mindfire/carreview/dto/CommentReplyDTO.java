package com.mindfire.carreview.dto;

import java.util.List;
/**
 * DTO For Comment Reply
 * @author mindfire
 *
 */
public class CommentReplyDTO {

	private Integer commentId;
	private Integer reviewId;
	private Integer parentId;
	private String comment;
	private String userName;
	private boolean show;
	private List<CommentReplyDTO> subComments;
	

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public Integer getCommentId() {
		return commentId;
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

	public String getUserName() {
		return userName;
	}

	public List<CommentReplyDTO> getSubComments() {
		return subComments;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
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

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setSubComments(List<CommentReplyDTO> subComments) {
		this.subComments = subComments;
	}

}
