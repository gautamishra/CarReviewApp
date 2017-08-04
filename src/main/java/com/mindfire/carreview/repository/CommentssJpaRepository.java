package com.mindfire.carreview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.carreview.model.Commentss;
/**
 * interface For Comment repository
 * @author mindfire
 *
 */
@Repository
public interface CommentssJpaRepository extends JpaRepository<Commentss, Integer> {

	List<Commentss> findByReviewReviewId(Integer id);
	List<Commentss> findByReviewReviewIdAndParentId(Integer id ,Integer id2);
	
}
