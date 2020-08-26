package com.hcl.MailSenderwithAttachment.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.MailSenderwithAttachment.service.ExcelService;
import com.hcl.MailSenderwithAttachment.service.MailSenderAttachService;

@RestController
@RequestMapping("Send")
public class MailSenderAttachController {

	@Autowired
	MailSenderAttachService mailSenderAttachService;
	@Autowired
	private ExcelService fileService;

	/**
	 * 
	 * @param filePath
	 * @param email    URL -
	 *                 localhost:8080/Send/EmailwithAttachment?email=<EmailID>&filePath=<FilePath>
	 */
	@GetMapping("EmailwithAttachment")
	public void sendEmailWithAttachment(@RequestParam("filePath") String filePath,
			@RequestParam("email") String email) {
		mailSenderAttachService.sendMailwithAttachment(email, filePath);
	}

	/**
	 * URL - localhost:8080/Send/download
	 */
	@PostMapping("/download")
	public ResponseEntity<Resource> getFile(@RequestBody List<JSONObject> json) {
		String filename = "fileName.xlsx";
		// System.out.println("Json is: " + json);
		InputStreamResource file = new InputStreamResource(fileService.load(json));

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}

}
