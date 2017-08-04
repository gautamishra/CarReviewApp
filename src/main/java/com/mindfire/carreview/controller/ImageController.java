package com.mindfire.carreview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.carreview.model.Image;
import com.mindfire.carreview.service.ImageService;
/**
 * Controller Class For Image
 * @author mindfire
 *
 */
@RestController
public class ImageController {

	@Autowired
	private ImageService imageService;
	@GetMapping("/images")
	public List<Image> getAllImage(){
		return imageService.findAllImage();
	}
	
	@GetMapping("/images/model/{id}")
	public List<Image> getImageByModel(@PathVariable(value = "id")Integer id){
		return imageService.findImageByModel(id);
	}
}
