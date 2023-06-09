package com.dto.doctor;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailToPatientReq {
	private int patientId;
	private String subject;
	private String text;
	private MultipartFile file;
}
