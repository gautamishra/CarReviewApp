package com.mindfire.carreview.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mindfire.carreview.dto.LoginDTO;
import com.mindfire.carreview.dto.ResetPasswordDTO;
import com.mindfire.carreview.dto.SuccessDTO;
import com.mindfire.carreview.dto.UserDTO;
import com.mindfire.carreview.dto.UserProfileDTO;
import com.mindfire.carreview.exception.GenerateExceptionApi;
import com.mindfire.carreview.model.User;
import com.mindfire.carreview.service.EmailServiceImpl;
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
	
	@Autowired
	private EmailServiceImpl emailServiceImpl;

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
	
	/**
	 * For Setting User Password
	 * @param loginDTO
	 * @return
	 * @throws GenerateExceptionApi
	 */
	@PostMapping("/user/setPassword")
	public User setPasswordForUser(@RequestBody  LoginDTO loginDTO) throws GenerateExceptionApi{
		return userService.postUserForSetPassword(loginDTO);
	}
	
	/**
	 * For getting 
	 * @throws GenerateExceptionApi 
	 */
	
	@GetMapping("/user/profileInfo/{id}")
	public UserProfileDTO getUserInfo(@PathVariable("id")Integer id ) throws GenerateExceptionApi{
		return userService.findUserInfo(id);
	}
	
	/**
	 * Create User With Image 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws IOException 
	 * @throws GenerateExceptionApi 
	 * @throws ParseException 
	 */
	@PostMapping("/user/image")
	public User createUserWithImage(@RequestParam("model")String str ,
									@RequestParam("file") MultipartFile  file) 
											throws JsonParseException, JsonMappingException
											,		IOException, GenerateExceptionApi {
		
		return userService.postUserWithImage(str , file);       
		};
	
	/**
	 * Send Email To user
	 * @throws GenerateExceptionApi 
	 */
		
	@PostMapping("/user/sendMail")
	public SuccessDTO sendEmail(@RequestBody LoginDTO loginDTO) throws GenerateExceptionApi{
		System.out.println(loginDTO.getUserName());
		return emailServiceImpl.sendSimpleMessageUsingTemplate(loginDTO.getUserName());
	}
	
	/**
	 * Reset password
	 */
	@PostMapping("/user/resetPassword")
	public SuccessDTO restsetPassword(@RequestBody  ResetPasswordDTO resetPasswordDTO)
				throws GenerateExceptionApi{
		return userService.resetPassword(resetPasswordDTO);
	}
}
	
	

