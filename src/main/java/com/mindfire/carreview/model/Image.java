package com.mindfire.carreview.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the image database table.
 * 
 */
@Entity
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="image_id")
	private Integer imageId;

	@Column(name="image_url")
	private String imageUrl;

	//bi-directional many-to-one association to ModelName
	@ManyToOne
	@JoinColumn(name="model_id")
	private ModelName modelName;

	public Image() {
	}

	public Integer getImageId() {
		return this.imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public ModelName getModelName() {
		return this.modelName;
	}

	public void setModelName(ModelName modelName) {
		this.modelName = modelName;
	}

}