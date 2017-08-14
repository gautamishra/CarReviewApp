package com.mindfire.carreview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.carreview.dto.LikeDTO;
import com.mindfire.carreview.model.Like;
import com.mindfire.carreview.repository.LikeJpaRepository;
import com.mindfire.carreview.repository.ReviewJpaRepository;
import com.mindfire.carreview.repository.UserJpaRepository;
/**
 * Service For Like entity
 * @author mindfire
 *
 */
@Service
public class LikeService {

	@Autowired
	private LikeJpaRepository likeJpaRepository;
	@Autowired
	private UserJpaRepository userJpaRepository;
	@Autowired
	private ReviewJpaRepository reviewJpaRepository;

	public List<Like> findAllLike() {
		return likeJpaRepository.findAll();
	}

	public Integer findLikeByReviewId(Integer id) {
		return likeJpaRepository.countByReviewReviewId(id);
	}

	public Like postLike(LikeDTO likeDTO) {
		Like like = new Like();
//		like.setUser(userJpaRepository.findOne(likeDTO.getUserId()));
		like.setReview(reviewJpaRepository.findOne(likeDTO.getReviewId()));
		return likeJpaRepository.saveAndFlush(like);
	}
	
	
}