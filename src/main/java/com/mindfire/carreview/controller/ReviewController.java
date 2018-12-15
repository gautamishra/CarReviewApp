package com.mindfire.carreview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.carreview.dto.ReviewDTO;
import com.mindfire.carreview.dto.ReviewUpdateDTO;
import com.mindfire.carreview.exception.GenerateExceptionApi;
import com.mindfire.carreview.model.Review;
import com.mindfire.carreview.service.ReviewService;
/**
 * Controller Class For Reviews
 * @author mindfire
 *
 */
@RestController
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	/**
	 * for returning all reviews
	 * @return
	 */
	
	@GetMapping("/reviews")
	public List<Review> getAllReviews() {
		return reviewService.findAllReviews();
	}
	
	/**
	 * For Returning Request pages 
	 */
	@GetMapping("/reviews/page/{pageNumber}")
	public Page<Review> getPageReviews(@PathVariable("pageNumber")int pageNumber) {
		
		return reviewService.findAllPageReviews(pageNumber);
	}

	/**
	 * To create Reviews
	 * @param reviewDTO
	 * @return
	 */
	
	@PostMapping("/reviews")
	public Review postReview(@RequestBody ReviewDTO reviewDTO){
		return reviewService.postReviews(reviewDTO);
	}
	
	/**
	 * For searching reviews
	 * @throws GenerateExceptionApi 
	 */
	@GetMapping("/reviews/search")
	public List<Review> getReviews(@RequestParam("manufacturerId") Integer manufacturerId
									           , @RequestParam("modelId")Integer modelId) throws GenerateExceptionApi{
		return reviewService.findReviews(manufacturerId,modelId);
	}
	
	/**
	 * search review By Their Id
	 */
	
	@GetMapping("/reviews/{id}")
	public Review getReviewById(@PathVariable(value = "id")Integer id){
		return reviewService.findByReviewId(id);
	}
	
	/**
	 * Update a review 
	 */
	@PostMapping("review/update")
	public Review updateReview(@RequestBody ReviewUpdateDTO reviewUpdateDTO){
		return reviewService.updateReview(reviewUpdateDTO);
	}
	
	/**
	 * Delete review 
	 * @throws GenerateExceptionApi 
	 */
	
	@DeleteMapping("review/delete/{id}")
	public void deleteReview(@PathVariable("id")Integer id) throws GenerateExceptionApi{
		 reviewService.deleteReview(id);
	}
	
}
