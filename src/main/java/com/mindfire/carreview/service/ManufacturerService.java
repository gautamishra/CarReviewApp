package com.mindfire.carreview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.carreview.model.Manufacturer;
import com.mindfire.carreview.repository.ManufacturerJpaRepository;
/**
 * Service Class For Manufacturer
 * @author mindfire
 *
 */
@Service
public class ManufacturerService {

	@Autowired
	private ManufacturerJpaRepository manufacturerJpaRepository;
	
	/**
	 * Getting all Manufacturer
	 * @return
	 */
	public List<Manufacturer> findAllManufacturer(){
//		return manufacturerJpaRepository.findAll();
		return manufacturerJpaRepository.findAllByOrderByManufacturerId();
	}
	/**
	 * getting Manufacturer By Id
	 * @param id
	 * @return
	 */
	public Manufacturer findManufacturer(Integer id){
		return manufacturerJpaRepository.findByManufacturerId(id);
	}
}
