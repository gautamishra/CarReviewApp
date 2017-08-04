package com.mindfire.carreview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.carreview.dto.CommentReplyDTO;
import com.mindfire.carreview.dto.CommentssDTO;
import com.mindfire.carreview.model.Commentss;
import com.mindfire.carreview.service.CommentssService;
/**
 * Controller Class For Comments
 * @author mindfire
 *
 */
@RestController
public class CommentssController {

	@Autowired
	private CommentssService commentssService;
	
/**
 * Getting All Comment
 * @return
 */
	@GetMapping("/comment")
	public List<Commentss> getAllComment() {
		return commentssService.findAllCommentss();
	}

	/**
	 * Creating a comment on review
	 * @param commentssDTO
	 * @return
	 */
	@PostMapping("/comment")
	public Commentss postCommentss(@RequestBody CommentssDTO commentssDTO ){
		return commentssService.postCommentss(commentssDTO);
	}
	
	/**
	 * Getting comment on any review by review ID
	 * @param id
	 * @return
	 */
	@GetMapping("comment/review/{id}")
	public List<Commentss> getCommentByReviewId(@PathVariable(value = "id")Integer id)
	{
		return commentssService.findCommentByReviewId(id);
	}
	
	/**
	 * get Complete Comment With  Reply on comment
	 *
	 */
	
	@GetMapping("/comment/reply/review/{id}")
	public List<CommentReplyDTO> getAllCommentReply(@PathVariable(value = "id")Integer id) {
		return commentssService.findAllCommentssReply(id,-1);
	}
}
