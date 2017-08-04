package com.mindfire.carreview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mindfire.carreview.dto.ReviewDTO;
import com.mindfire.carreview.exception.GenerateExceptionApi;
import com.mindfire.carreview.model.Manufacturer;
import com.mindfire.carreview.model.ModelName;
import com.mindfire.carreview.model.Review;
import com.mindfire.carreview.repository.ManufacturerJpaRepository;
import com.mindfire.carreview.repository.ModelNameJpaRepository;
import com.mindfire.carreview.repository.ReviewJpaRepository;
import com.mindfire.carreview.repository.UserJpaRepository;

/**
 * Service Class For Review Entity
 * 
 * @author mindfire
 *
 */
@Service
public class ReviewService {

	@Autowired
	private ReviewJpaRepository reviewJpaRepository;

	@Autowired
	private ModelNameJpaRepository modelNameJpaRepository;

	@Autowired
	private ManufacturerJpaRepository manufacturerJpaRepository;

	@Autowired
	private UserJpaRepository userJpaRepository;

	/**
	 * Getting all Reviews
	 * 
	 * @return
	 */
	public List<Review> findAllReviews() {
		return reviewJpaRepository.findAll();
	}

	/**
	 * Creating a Review
	 * 
	 * @param review
	 * @return
	 */
	public Review postReviews(Review review) {

		return reviewJpaRepository.saveAndFlush(review);
	}

	/**
	 * Creating A review 
	 * @param reviewDTO
	 * @return
	 */
	public Review postReviews(ReviewDTO reviewDTO) {
		// get manufacturer Object
		Manufacturer manufacturer = manufacturerJpaRepository.findByManufacturerId(reviewDTO.getManufacturerId());

		// get Model Object
		ModelName model = modelNameJpaRepository.findOne(reviewDTO.getModelId());

		Review review = new Review();
		review.setManufacturer(manufacturer);
		review.setModelName(model);
		review.setUser(userJpaRepository.findOne(reviewDTO.getUserId()));
		review.setTitle(reviewDTO.getTitle());
		review.setRating(reviewDTO.getRating());
		review.setWhatGood(reviewDTO.getWhatGood());
		review.setWhatImprove(reviewDTO.getWhatImprove());
		review.setMileage(reviewDTO.getMileage());
		review.setMaintanenceCost(reviewDTO.getMaintanenceCost());
		review.setAnyComment(reviewDTO.getAnyComment());
		review.setAddPhoto(reviewDTO.getAddPhoto());

		return reviewJpaRepository.saveAndFlush(review);
	}

	/**
	 * Getting reviews Based On Manufacturer Id And Model Id
	 * 
	 * @param manufacturerId
	 * @param modelId
	 * @return
	 * @throws GenerateExceptionApi
	 */
	public List<Review> findReviews(Integer manufacturerId, Integer modelId) throws GenerateExceptionApi {
		List<Review> list = reviewJpaRepository.findByManufacturerManufacturerIdAndModelNameModelId(manufacturerId,
				modelId);

		if (list.isEmpty() == true) {
			throw new GenerateExceptionApi("no reviews are available for your Choice", HttpStatus.BAD_REQUEST);
		}
		return list;
	}

	/**
	 * For getting review By ID
	 * 
	 * @param id
	 * @return
	 */
	public Review findByReviewId(Integer id) {

		return reviewJpaRepository.findOne(id);
	}

}
