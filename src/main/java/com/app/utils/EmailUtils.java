package com.app.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailUtils {
	
	private JavaMailSender sendMail;
	
	public  void sendEmail(String to, String subject, String body)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		
		sendMail.send(message);
		
	}

}
