package com.mindfire.carreview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.carreview.model.Manufacturer;
/**
 *JPA Repository Interface For Manufcaturer Entity
 * @author mindfire
 *
 */
@Repository
public interface ManufacturerJpaRepository extends JpaRepository<Manufacturer, Integer> {
	Manufacturer findByManufacturerId(Integer id);
	List<Manufacturer> findAllByOrderByManufacturerId();
}
