package com.mindfire.carreview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.carreview.model.Manufacturer;
import com.mindfire.carreview.service.ManufacturerService;
/**
 * Controller Class For Manufacturer
 * @author mindfire
 *
 */
@RestController()
public class ManufacturerController {

	@Autowired
	private ManufacturerService manufacturerService;
	
	/**
	 * Method For Getting All Manufacturer
	 * @return
	 */
	
	@GetMapping("/manufacturer")
	List<Manufacturer> getAllManufacturer()
	{
		return manufacturerService.findAllManufacturer();
	}
	
	/**
	 * For getting Any Particular Manufacturer
	 * @param id
	 * @return
	 */
	
	@GetMapping("/manufacturer/{id}")
	Manufacturer getManufacturer(@PathVariable(value = "id")Integer id)
	{
		return manufacturerService.findManufacturer(id);
	}
}
