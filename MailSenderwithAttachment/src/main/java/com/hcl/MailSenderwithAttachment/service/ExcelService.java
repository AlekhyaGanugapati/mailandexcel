package com.hcl.MailSenderwithAttachment.service;

import java.io.InputStream;
import java.util.List;

import org.json.simple.JSONObject;


public interface ExcelService {


	public InputStream load(List<JSONObject> json);


}
