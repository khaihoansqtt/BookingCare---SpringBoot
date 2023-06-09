package com.dto.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailToPatientRes {
	private int patientId;
	private String subject;
	private String text;
	private String attachedFile;
}
