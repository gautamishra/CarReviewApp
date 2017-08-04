package com.mindfire.carreview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.carreview.dto.LoginDTO;
import com.mindfire.carreview.dto.UserDTO;
import com.mindfire.carreview.exception.GenerateExceptionApi;
import com.mindfire.carreview.model.User;
import com.mindfire.carreview.service.UserService;

/**
 * Controllers Class For User
 * @author mindfire
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

/**
 * Getting All User
 * @return
 */
	
	@GetMapping("/user")
	public List<User> getAllUser() {
		return userService.findAllUser();
	}
	

	/**
	 * To Create a user
	 * @param user
	 * @return
	 * @throws GenerateExceptionApi 
	 */
	
	@PostMapping("/user")
	public User postUser(@RequestBody  UserDTO userDTO) throws GenerateExceptionApi{
		return userService.postUser(userDTO);
	}
	

	/**
	 * Create User For Comment 
	 * @throws GenerateExceptionApi 
	 */
	@PostMapping("/user/comment")
	public User postUserForComment(@RequestBody  UserDTO userDTO) throws GenerateExceptionApi{
		return userService.postUserForComment(userDTO);
	}
	
	
	
	/**
	 * For getting all users from db by their Id
	 * @param id
	 * @return
	 */
	@GetMapping("/user/{id}")
	public List<User> getUser(@PathVariable(value = "id")Integer id){
		return userService.findUser(id);
	}
	
	/**
	 * Log in Authentication 
	 * @throws GenerateExceptionApi 
	 */
	@PostMapping("/user/login")
	public User getUserInfo(@RequestBody LoginDTO loginDTO) throws GenerateExceptionApi{
		return userService.findUserLogin(loginDTO);
	}
	
}
