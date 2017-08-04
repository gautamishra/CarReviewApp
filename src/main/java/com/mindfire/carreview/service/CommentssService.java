package com.mindfire.carreview.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.carreview.dto.CommentReplyDTO;
import com.mindfire.carreview.dto.CommentssDTO;
import com.mindfire.carreview.model.Commentss;
import com.mindfire.carreview.repository.CommentssJpaRepository;
import com.mindfire.carreview.repository.ReviewJpaRepository;
import com.mindfire.carreview.repository.UserJpaRepository;
/**
 * Service Class For Commentss
 * @author mindfire
 *
 */
@Service
public class CommentssService {

	@Autowired
	private CommentssJpaRepository commentssJpaRepository;

	@Autowired
	private UserJpaRepository userJpaRepository;
	@Autowired
	private ReviewJpaRepository reviewJpaRepository;
/**
 * Find All Comments 
 * @return
 */
	
	public List<Commentss> findAllCommentss() {
		return commentssJpaRepository.findAll();
	}

	/**
	 * for Posting Comments On Review 
	 * @param commentssDTO
	 * @return
	 */
	public Commentss postCommentss(CommentssDTO commentssDTO) {

		Commentss comment = new Commentss();
		comment.setUser(userJpaRepository.findOne(commentssDTO.getUserId()));
		comment.setReview(reviewJpaRepository.findOne(commentssDTO.getReviewId()));
		comment.setParentId(commentssDTO.getParentId());
		comment.setComment(commentssDTO.getComment());
		return commentssJpaRepository.saveAndFlush(comment);
	}
	
	/**
	 * Getting Comment On a review
	 * @param id
	 * @return
	 */
	public List<Commentss> findCommentByReviewId(Integer id) {

		return commentssJpaRepository.findByReviewReviewId(id);
	}

	/**
	 * method For getting Reply
	 * @param id 
	 * @param i 
	 * @return
	 */
	public List<CommentReplyDTO> findAllCommentssReply(Integer id, Integer parentId) {
		List<CommentReplyDTO> listCommentReply  = new ArrayList<>();
//		CommentReplyDTO commentReply = new CommentReplyDTO(); 
		List<Commentss> comments;
		
		comments = commentssJpaRepository.findByReviewReviewIdAndParentId(id,parentId);
		
		comments.forEach(comment->{
			CommentReplyDTO commentReply = new CommentReplyDTO(); 
			commentReply.setCommentId(comment.getCommentId());
			commentReply.setUserName(comment.getUser().getName());
			commentReply.setComment(comment.getComment());
			commentReply.setParentId(comment.getParentId());
			commentReply.setReviewId(comment.getReview().getReviewId());
			commentReply.setShow(false);
			commentReply.setSubComments(findAllCommentssReply(comment.getReview().getReviewId() , comment.getCommentId()));
			listCommentReply.add(commentReply);
		});
		
		return listCommentReply;
	}

	

}
