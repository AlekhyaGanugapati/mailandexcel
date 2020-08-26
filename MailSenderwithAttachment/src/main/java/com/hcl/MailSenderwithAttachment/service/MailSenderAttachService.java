package com.hcl.MailSenderwithAttachment.service;

import org.springframework.stereotype.Service;

@Service
public interface MailSenderAttachService {
	public void sendMailwithAttachment(String Email, String filePath);
}
