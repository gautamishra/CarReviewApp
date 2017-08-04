package com.mindfire.carreview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.carreview.model.Image;
/**
 * Interface For Image repository
 * @author mindfire
 *
 */
@Repository
public interface ImageJpaRepository extends JpaRepository<Image, Integer> {

	List<Image> findByModelNameModelId(Integer id);
}
