package com.mindfire.carreview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.carreview.model.ModelName;
/**
 * Interface for ModelName Repository
 * @author mindfire
 *
 */
@Repository
public interface ModelNameJpaRepository extends JpaRepository<ModelName, Integer> {

	List<ModelName> findByModelId(Integer id);
	List<ModelName> findByManufacturerManufacturerId(Integer id);
}
