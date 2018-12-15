package com.mindfire.carreview.service;

import java.io.File;
import java.security.SecureRandom;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mindfire.carreview.dto.SuccessDTO;
import com.mindfire.carreview.exception.GenerateExceptionApi;
import com.mindfire.carreview.model.User;
import com.mindfire.carreview.repository.UserJpaRepository;

@Service
public class EmailServiceImpl implements EmailService {
  
    @Autowired
    public JavaMailSender emailSender;
    @Autowired
    private SimpleMailMessage template;
    @Autowired
    private UserJpaRepository userJpaRepository;
   
 
    public String sendSimpleMessage(
      String to, String subject, String text) {
        
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        try{
        emailSender.send(message);
        }
        catch(Exception e){
        	return e.getMessage();
        }
        return "Your email sent";
    }

	

	@Override
	public String sendMessageWithAttachment(String to, String subject, String text, 
															String pathToAttachment) {
		MimeMessage message = emailSender.createMimeMessage();
	      
	    MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
		
	     
		    helper.setTo(to);
		    helper.setSubject(subject);
		    helper.setText(text);
		         
		    FileSystemResource file 
		      = new FileSystemResource(new File(pathToAttachment));
		    helper.addAttachment("Invoice", file);
		 
		    emailSender.send(message);
		    return "your message has been sent";
	   
		} catch (MessagingException e) {
			
			return e.getMessage();
		}
		
	}

	/**
	 * For Sending Reset Password Mail to User
	 */


	@Override
	public SuccessDTO sendSimpleMessageUsingTemplate(String to) throws GenerateExceptionApi {	
		User user = userJpaRepository.findByEmail(to);
		if(user != null){
			SecureRandom random = new SecureRandom();
			byte bytes[] = new byte[40];
			random.nextBytes(bytes);
			String token = bytes.toString();
			String link = "http://localhost:8080/carreviewfrontend/public/#!/resetPassword/"+user.getUserId()+"/"+token;		
			user.setResetToken(token);
			userJpaRepository.saveAndFlush(user);
			String text = String.format(template.getText(),user.getName(),link);
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper;
			try{
				helper = new MimeMessageHelper(message , true);
				helper.setTo(to);
				helper.setSubject("Password Reset Link");
				helper.setText(text, true);
				SuccessDTO successDTO = new SuccessDTO();
				emailSender.send(message);
				successDTO.setReponse("Email has Been sent");
				return successDTO;
			}
			catch(Exception e)
			{
				throw new GenerateExceptionApi(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
		}
		else
			{
			throw new GenerateExceptionApi("Email Address Does Not Exist!!" , HttpStatus.BAD_REQUEST);
			
			}			
	}

	@Bean
	public SimpleMailMessage templateSimpleMessage() {
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setText("<DOCTYPE !html>"
	    		+ "<html>"
	    		+ "<head>"
	    		+ "</head>"
	    		+ "<body>"
	    		+ "<h2>Greeting From carreview</h2>"
	    		+ "\n%s\n"
	     + "<p>Please click on the Following Link To Reset the password</p>"
	     + "<a href='%s'>Reset Password</a>"
	      
	      + "</body>"
	      + "</html>");
	    return message;
	}
	
}

