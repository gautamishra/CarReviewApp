package com.mindfire.carreview.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mindfire.carreview.dto.LoginDTO;
import com.mindfire.carreview.dto.UserDTO;
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
		 	else{
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
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
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

	
	
	
}
