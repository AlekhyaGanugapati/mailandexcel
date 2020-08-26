package com.hcl.MailSenderwithAttachment.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSenderAttachServiceImpl implements MailSenderAttachService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendMailwithAttachment(String Email, String filePath) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(Email);
			helper.setSubject("Trail1");
			helper.setText("Helloo!!");
			FileSystemResource file = new FileSystemResource(filePath);
			helper.addAttachment(file.getFilename(), file);

		} catch (MailException mex) {
			System.err.println(mex.getMessage());
		} catch (MessagingException msgEx) {
			System.err.println(msgEx.getMessage());
		}
		javaMailSender.send(message);
	}

}
