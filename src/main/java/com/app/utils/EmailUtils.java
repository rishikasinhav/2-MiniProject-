package com.app.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	
	private JavaMailSender sendMail;
	
	public  boolean sendEmail(String to, String subject, String body)
	{
		boolean isSent=false;
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			
			message.setTo(to);
			message.setSubject(subject);
			message.setText(body);
			
			sendMail.send(message);
			isSent=true;
			return isSent;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return isSent;
		
	}

}
