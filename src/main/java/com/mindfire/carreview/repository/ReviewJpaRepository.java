package com.mindfire.carreview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.carreview.model.Review;
/**
 * Interface For Review Repository
 * @author mindfire
 *
 */
@Repository
public interface ReviewJpaRepository extends JpaRepository<Review, Integer> {
	List<Review> findByManufacturerManufacturerIdAndModelNameModelId(Integer manufacturerId , Integer modelId);
}
