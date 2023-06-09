package com.dto.doctor;

import com.dto.MedicalAppointmentOnlyPatientDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmMedicalAppointmentRes {
	private String message;
	private MedicalAppointmentOnlyPatientDTO medicalAppointmentOnlyPatientDTO;
}
