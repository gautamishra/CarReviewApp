package com.mindfire.carreview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.carreview.model.Image;
import com.mindfire.carreview.repository.ImageJpaRepository;
/**
 * Service For Image 
 * @author mindfire
 *
 */
@Service
public class ImageService {

	@Autowired
	private ImageJpaRepository imageJpaRepository;
	/**
	 * Getting all Image Table
	 * @return
	 */
	public List<Image> findAllImage() {
		return imageJpaRepository.findAll();
	}

	/**
	 * Getting Image For particular Model Id
	 * @param id
	 * @return
	 */
	public List<Image> findImageByModel(Integer id) {
		return imageJpaRepository.findByModelNameModelId(id);
	}
	
	
}
