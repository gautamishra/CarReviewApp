package com.mindfire.carreview.service;

import com.mindfire.carreview.dto.SuccessDTO;
import com.mindfire.carreview.exception.GenerateExceptionApi;

public interface EmailService {
    String sendSimpleMessage(String to,
                           String subject,
                           String text);
    
    SuccessDTO sendSimpleMessageUsingTemplate(String to)throws GenerateExceptionApi;
    
    String sendMessageWithAttachment(String to,
                                   String subject,
                                   String text,
                                   String pathToAttachment);
}

