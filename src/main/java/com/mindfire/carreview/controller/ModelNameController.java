package com.mindfire.carreview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.carreview.model.ModelName;
import com.mindfire.carreview.service.ModelNameService;
/**
 * Controller For ModelName Class
 * @author mindfire
 *
 */
@RestController
public class ModelNameController {

	@Autowired
	private ModelNameService modelNameService;
	/**
	 * For Getting All CarModel
	 * @return
	 */
	@GetMapping("/carmodel")
	public List<ModelName> getAllModelName(){
	return	modelNameService.findAllModelName();
	}
	
	/**
	 * For Car Model Based On Its Id
	 * @param id
	 * @return
	 */
	@GetMapping("/carmodel/{id}")
	public List<ModelName> getModelName(@PathVariable(value = "id") Integer id){
		return modelNameService.findModelName(id);
	}
	
	/**
	 * For Getting CarModel Based On Manufacturer
	 * @param id
	 * @return
	 */
	
	@GetMapping("/carmodel/manuf/{id}")
	public List<ModelName> getModelNameOfManufacturer(@PathVariable(value = "id") Integer id){
		return modelNameService.findModelNameOfManufacturer(id);
	}
}
