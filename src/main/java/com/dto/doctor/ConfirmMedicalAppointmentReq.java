package com.dto.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmMedicalAppointmentReq {
	private int medicalAppointmentId;
	private String cancelReason;
}
