package com.mindfire.carreview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.carreview.dto.LikeDTO;
import com.mindfire.carreview.model.Like;
import com.mindfire.carreview.service.LikeService;
/**
 * Controller Class For Like 
 * @author mindfire
 *
 */
@RestController
public class LikeController {
	
	@Autowired
	private LikeService likeService;
	
	@GetMapping("/like")
	public List<Like> getAllLike(){
		return likeService.findAllLike();
	}
	
	/**
	 * Getting Like On Particular Review
	 * @param id
	 * @return
	 */
	@GetMapping("/like/review/{id}")
	public List<Like> getReviewLike(@PathVariable(value = "id") Integer id){
		return  likeService.findLikeByReviewId(id);
	}
	
	/**
	 * for Creating A Like On Any Review
	 * @param likeDTO
	 * @return
	 */
	@PostMapping("/like")
	public Like postLike(@RequestBody LikeDTO likeDTO){
		return likeService.postLike(likeDTO);
	}
}
