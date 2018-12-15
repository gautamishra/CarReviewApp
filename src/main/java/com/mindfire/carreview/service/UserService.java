package com.mindfire.carreview.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindfire.carreview.dto.LoginDTO;
import com.mindfire.carreview.dto.ResetPasswordDTO;
import com.mindfire.carreview.dto.SuccessDTO;
import com.mindfire.carreview.dto.UserDTO;
import com.mindfire.carreview.dto.UserProfileDTO;
import com.mindfire.carreview.exception.GenerateExceptionApi;
import com.mindfire.carreview.model.User;
import com.mindfire.carreview.repository.UserJpaRepository;
/**
 * Service Class For User Entity
 * @author mindfire
 *
 */
@Service
public class UserService {

	@Autowired
	private UserJpaRepository userJpaRepository;
	
	public List<User> findAllUser(){
		return userJpaRepository.findAll();
	}

	/**
	 * Creating a User 
	 * @param userDTO
	 * @return
	 * @throws GenerateExceptionApi 
	 */
	
	public User postUser(UserDTO userDTO) throws GenerateExceptionApi {
		 	User user;
		 	user =  userJpaRepository.findByEmail(userDTO.getEmail());
		 	if(user != null){
		 		throw new GenerateExceptionApi("email already exist please Log in" , HttpStatus.BAD_REQUEST);
		 		
		 	}
		 	user = new User();
			user.setName(userDTO.getName());
			user.setEmail(userDTO.getEmail());
			user.setPhone(userDTO.getPhone());
			
			String password = userDTO.getPassword();
			String newPassword = encryptPassword(password);
			user.setPassword(newPassword);
			user.setSignUp(true);
			return userJpaRepository.saveAndFlush(user);
		 	
		 	
		 
	}
	
	
	/**
	 * Create user For Comment 
	 * @param id
	 * @return
	 */
	
	public User postUserForComment(UserDTO userDTO)  {
				User user ;
			 	user =  userJpaRepository.findByEmail(userDTO.getEmail());
			 	
			 	if(user != null){
			 		return user;
			 	}
			 	
			 		user = new User();
			 		user.setName(userDTO.getName());
					user.setEmail(userDTO.getEmail());
					user.setPhone(userDTO.getPhone());
					user.setSignUp(false);
					return userJpaRepository.saveAndFlush(user);
			
	}
	
	public List<User> findUser(Integer id){
		return userJpaRepository.findByUserId(id);
	}

	/**
	 * Generating Encrypted MD5 password
	 */
	
	public static  String encryptPassword(String password){
		String passwordToHash = password;
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        System.out.println(generatedPassword);
        
        return generatedPassword;
    }

	/**
	 * Checking user login 
	 * @param loginDTO
	 * @return
	 * @throws GenerateExceptionApi
	 */
	
	public User findUserLogin(LoginDTO loginDTO) throws GenerateExceptionApi {
		
		 User user;
		 user =  userJpaRepository.findByEmail(loginDTO.getUserName());
		if(user == null) {
			throw new GenerateExceptionApi("user does not exist you need to register first"
											,HttpStatus.BAD_REQUEST);
		}
		
		String newPassword = loginDTO.getPassword();
		
		if(user.getPassword() == null){
			throw new GenerateExceptionApi("You need to set Your Password first"
					,HttpStatus.BAD_REQUEST);
		}
		String storedPassword = user.getPassword();
		
		String md5Password  = encryptPassword(newPassword);
		if(md5Password.equals(storedPassword)){
			return user;
		}
		
		throw new GenerateExceptionApi("Password is wrong Try again"
				,HttpStatus.BAD_REQUEST);
		
	}

	/**
	 * Set Password Of preRegistered User
	 * @param loginDTO
	 * @return
	 */
	
	public User postUserForSetPassword(LoginDTO loginDTO) {
		User user ;
	 	user =  userJpaRepository.findByEmail(loginDTO.getUserName());
	 	String password = loginDTO.getPassword();
	 	String newPassword = encryptPassword(password);
		user.setPassword(newPassword);
		user.setSignUp(true);
		return userJpaRepository.saveAndFlush(user);
	}

	/**
	 * return  Profile of User
	 * @param id
	 * @return
	 * @throws GenerateExceptionApi
	 */
	public UserProfileDTO findUserInfo(Integer id) throws GenerateExceptionApi {
		User user ;
	 	user =  userJpaRepository.findOne(id);
		if(user == null){
			throw new GenerateExceptionApi("user does not exist you need to register first"
					,HttpStatus.BAD_REQUEST);
		}
		UserProfileDTO userProfileDto = new UserProfileDTO();
		userProfileDto.setEmail(user.getEmail());
		userProfileDto.setName(user.getName());
		userProfileDto.setPhone(user.getPhone());
		userProfileDto.setReview(user.getReviews());
		if(user.getImage() == null)
			userProfileDto.setImage("empty.png");
		else
			userProfileDto.setImage(user.getImage());
		return userProfileDto;
	}

	
	/**
	 * Creating User With Image 
	 * @param str
	 * @param file
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws GenerateExceptionApi 
	 */
	public User postUserWithImage(String str, MultipartFile file) throws JsonParseException, 
									JsonMappingException, IOException, GenerateExceptionApi {
		
		
		String a[];
		String ext ;
		String name = "";
		String imageName = "";
        String contentType = file.getContentType();
        User user;
        UserDTO userDTO;
        
//        parsing string data in User Entity
        
        userDTO =  new ObjectMapper().readValue(str, UserDTO.class);
        
        user =  userJpaRepository.findByEmail(userDTO.getEmail());
        
        if(user != null)
        {
        	throw new GenerateExceptionApi("email already exist please Log in",
        												HttpStatus.BAD_REQUEST);
        }
        
        if (!file.isEmpty()) {
        	
        a = contentType.split("/");
        ext = a[1];
        a = userDTO.getEmail().split("@");
        name = a[0];
        imageName = name + "." + ext;
			try {
				byte[] bytes = file.getBytes();
				
				File dir = new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\UserImage");
				File serverFile = new File(dir
						+ File.separator + imageName);
				System.out.println(serverFile);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				
				stream.write(bytes);
				stream.close();	
			}
			catch (Exception e) {
				throw new GenerateExceptionApi("You failed to upload " 
						+ file.getOriginalFilename() + " => " + e.getMessage(),HttpStatus.BAD_REQUEST
						);
			}
        }
        
        user = new User();
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPhone(userDTO.getPhone());
		
		String password = userDTO.getPassword();
		String newPassword = encryptPassword(password);
		user.setPassword(newPassword);
		user.setSignUp(true);
		if(!file.isEmpty())
			user.setImage(imageName);
		
		return userJpaRepository.saveAndFlush(user);
        	
		
	}

	/**
	 * reset User Password 
	 * @param loginDTO
	 * @return
	 * @throws GenerateExceptionApi 
	 */
	
	public SuccessDTO resetPassword(ResetPasswordDTO resetPasswordDTO) throws GenerateExceptionApi {
		User user ;
	 	user =  userJpaRepository.findOne(resetPasswordDTO.getUserId());
	 	if(user !=null){
	 		if(user.getResetToken().equals(resetPasswordDTO.getToken())){
				 	String password = resetPasswordDTO.getPassword();
				 	String newPassword = encryptPassword(password);
					user.setPassword(newPassword);
					userJpaRepository.saveAndFlush(user);
					 SuccessDTO successDTO = new SuccessDTO();
					 successDTO.setReponse("Your Password has Been reset !!");
					 return successDTO;
	 			}
	 		else{
	 			throw new GenerateExceptionApi("Again generate link"
		 				, HttpStatus.BAD_REQUEST);
	 		}
	 	}
	 	else{
	 		throw new GenerateExceptionApi("Your Email is not registerd with us"
	 				, HttpStatus.BAD_REQUEST);
	 	}
	}
	
}
