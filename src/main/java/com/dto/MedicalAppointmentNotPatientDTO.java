package com.dto;

import com.entity.HistoryAppointment;
import com.entity.MedicalAppointment;

public class MedicalAppointmentNotPatientDTO extends MedicalAppointmentDTO {
	public MedicalAppointmentNotPatientDTO(MedicalAppointment medicalAppointment) {
		setId(medicalAppointment.getId());
		setTime(medicalAppointment.getTime());
		setPrice(medicalAppointment.getPrice());
		setName(medicalAppointment.getName());
		setGender(medicalAppointment.getTime());
		setPhone(medicalAppointment.getTime());
		setAddress(medicalAppointment.getTime());
		setReason(medicalAppointment.getReason());
		setIsConfirmed(medicalAppointment.getIsConfirmed());
		setCancelReason(medicalAppointment.getCancelReason());
		setDoctor(new DoctorNotRelationDTO(medicalAppointment.getDoctor()));
		
		HistoryAppointment historyAppointment = medicalAppointment.getHistoryAppointment();
		setHistoryAppointment(historyAppointment != null ? new HistoryAppointmentNotRelationDTO(historyAppointment) : null);
	}
}
