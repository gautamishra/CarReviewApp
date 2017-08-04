package com.mindfire.carreview.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the model_name database table.
 * 
 */
@Entity
@Table(name="model_name")
public class ModelName implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="model_id")
	private Integer modelId;

	@Column(name="model_name")
	private String modelName;

//	//bi-directional many-to-one association to Image
//	@OneToMany(mappedBy="modelName")
//	private List<Image> images;

	//bi-directional many-to-one association to Manufacturer
	@ManyToOne
	@JoinColumn(name="manufacturer_id")
	private Manufacturer manufacturer;

//	//bi-directional many-to-one association to Review
//	@OneToMany(mappedBy="modelName")
//	private List<Review> reviews;

	public ModelName() {
	}

	public Integer getModelId() {
		return this.modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

//	public List<Image> getImages() {
//		return this.images;
//	}
//
//	public void setImages(List<Image> images) {
//		this.images = images;
//	}

//	public Image addImage(Image image) {
//		getImages().add(image);
//		image.setModelName(this);
//
//		return image;
//	}
//
//	public Image removeImage(Image image) {
//		getImages().remove(image);
//		image.setModelName(null);
//
//		return image;
//	}

	public Manufacturer getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
//
//	public List<Review> getReviews() {
//		return this.reviews;
//	}
//
//	public void setReviews(List<Review> reviews) {
//		this.reviews = reviews;
//	}

//	public Review addReview(Review review) {
//		getReviews().add(review);
//		review.setModelName(this);
//
//		return review;
//	}
//
//	public Review removeReview(Review review) {
//		getReviews().remove(review);
//		review.setModelName(null);
//
//		return review;
//	}

}