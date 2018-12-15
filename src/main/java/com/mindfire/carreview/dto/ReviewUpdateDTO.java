package com.mindfire.carreview.dto;
/**
 * DTO class review
 * @author mindfire
 *
 */
public class ReviewUpdateDTO {

	private Integer reviewId;
	private Integer manufacturerId;
	private Integer modelId;
	private Integer userId;
	private String title;
	private Integer rating;
	private String whatGood;
	private String whatImprove;
	private Integer mileage;
	private Integer maintanenceCost;
	private String anyComment;
	
	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public void setAddPhoto(Byte[] addPhoto) {
		this.addPhoto = addPhoto;
	}

	private Byte[] addPhoto;

	public Integer getUserId() {
		return userId;
	}

	public String getTitle() {
		return title;
	}

	public Integer getRating() {
		return rating;
	}

	public String getWhatGood() {
		return whatGood;
	}

	public String getWhatImprove() {
		return whatImprove;
	}

	public Integer getMileage() {
		return mileage;
	}

	public Integer getMaintanenceCost() {
		return maintanenceCost;
	}

	public String getAnyComment() {
		return anyComment;
	}

	public Byte[] getAddPhoto() {
		return addPhoto;
	}

	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setRating(Integer reating) {
		this.rating = reating;
	}

	public void setWhatGood(String whtatGood) {
		this.whatGood = whtatGood;
	}

	public void setWhatImprove(String whatImprove) {
		this.whatImprove = whatImprove;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public void setMaintanenceCost(Integer maintanenceCost) {
		this.maintanenceCost = maintanenceCost;
	}

	public void setAnyComment(String anyComment) {
		this.anyComment = anyComment;
	}

	public void setAdPhoto(Byte[] addPhoto) {
		this.addPhoto = addPhoto;
	}
	

	public int getManufacturerId() {
		return manufacturerId;
	}

	public int getModelId() {
		return modelId;
	}

	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
}
