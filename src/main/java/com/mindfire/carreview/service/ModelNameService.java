package com.mindfire.carreview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.carreview.model.ModelName;
import com.mindfire.carreview.repository.ModelNameJpaRepository;
/**
 * Service Class For ModelName Entity
 * @author mindfire
 *
 */
@Service
public class ModelNameService {

	@Autowired
	private ModelNameJpaRepository modelNameRrpository;
	
	/**
	 * Getting All Model Name
	 * @return
	 */
	public List<ModelName> findAllModelName(){
		return modelNameRrpository.findAll();
	}
	
	/**
	 * Getting Model Name By Id
	 * @param id
	 * @return
	 */
	public List<ModelName> findModelName(Integer id) {
		
		return modelNameRrpository.findByModelId(id);
	}
	/**
	 * Getting Model Name For Manufacturer Id 
	 * @param id
	 * @return
	 */
	public List<ModelName> findModelNameOfManufacturer(Integer id) {
		return modelNameRrpository.findByManufacturerManufacturerId(id);
	}
}
