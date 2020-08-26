package com.hcl.MailSenderwithAttachment.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ExcelServiceImpl implements ExcelService {

	@Override
	public InputStream load(List<JSONObject> json) {
		// TODO Auto-generated method stub
		ByteArrayInputStream out = this.convertToExcel(json);
		return out;
	}

	private ByteArrayInputStream convertToExcel(List<JSONObject> json) {

		try {
			Workbook workbook = new XSSFWorkbook();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Sheet sheet = workbook.createSheet("sheetName");

			// Header
			Row headerRow = sheet.createRow(0);
			Set headersSet = json.get(0).keySet();
			List<String> headers = new ArrayList(headersSet);

			for (int col = 0; col < headers.size(); col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue((String) headers.get(col));
			}

			int rowIdx = 1;
			for (JSONObject single : json) {
				Row row = sheet.createRow(rowIdx++);
				for (int col = 0; col < headers.size(); col++) {
					String key = headers.get(col);
					Object value = single.get(key);
					String dataType = value.getClass().getSimpleName();
					if (dataType.equalsIgnoreCase("Integer")) {
						row.createCell(col).setCellValue((Integer) single.get(key));

					} else if (dataType.equalsIgnoreCase("Long")) {
						row.createCell(col).setCellValue((Long) single.get(key));

					} else if (dataType.equalsIgnoreCase("Float")) {
						row.createCell(col).setCellValue((Float) single.get(key));

					} else if (dataType.equalsIgnoreCase("Double")) {
						row.createCell(col).setCellValue((Double) single.get(key));

					} else if (dataType.equalsIgnoreCase("Boolean")) {
						row.createCell(col).setCellValue((Boolean) single.get(key));

					} else if (dataType.equalsIgnoreCase("String")) {
						row.createCell(col).setCellValue((String) single.get(key));

					}
				}

			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}

}
