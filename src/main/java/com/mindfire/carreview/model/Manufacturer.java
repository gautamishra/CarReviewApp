package com.mindfire.carreview.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the manufacturer database table.
 * 
 */
@Entity
public class Manufacturer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="manufacturer_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer manufacturerId;

	@Column(name="manufacturer_name")
	private String manufacturerName;

//	/*//bi-directional many-to-one association to ModelName
//	@OneToMany(mappedBy="manufacturer")
//	private List<ModelName> modelNames;
//
//	//bi-directional many-to-one association to Review
//	@OneToMany(mappedBy="manufacturer")
//	private List<Review> reviews;
	public Manufacturer() {
	}

	public Integer getManufacturerId() {
		return this.manufacturerId;
	}

	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getManufacturerName() {
		return this.manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
//
//	public List<ModelName> getModelNames() {
//		return this.modelNames;
//	}
//
//	public void setModelNames(List<ModelName> modelNames) {
//		this.modelNames = modelNames;
//	}

//	public ModelName addModelName(ModelName modelName) {
//		getModelNames().add(modelName);
//		modelName.setManufacturer(this);
//
//		return modelName;
//	}
//
//	public ModelName removeModelName(ModelName modelName) {
//		getModelNames().remove(modelName);
//		modelName.setManufacturer(null);
//
//		return modelName;
//	}

//	public List<Review> getReviews() {
//		return this.reviews;
//	}
//
//	public void setReviews(List<Review> reviews) {
//		this.reviews = reviews;
//	}

//	public Review addReview(Review review) {
//		getReviews().add(review);
//		review.setManufacturer(this);
//
//		return review;
//	}
//
//	public Review removeReview(Review review) {
//		getReviews().remove(review);
//		review.setManufacturer(null);
//
//		return review;
//	}

}