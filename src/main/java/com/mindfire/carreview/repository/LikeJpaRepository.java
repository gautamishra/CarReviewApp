package com.mindfire.carreview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.carreview.model.Like;
/**
 * Interface For Like Repository
 * @author mindfire
 *
 */
@Repository
public interface LikeJpaRepository extends JpaRepository<Like, Integer> {
	
	List<Like> findByReviewReviewId(Integer id);
	Integer countByReviewReviewId(Integer id);
}
