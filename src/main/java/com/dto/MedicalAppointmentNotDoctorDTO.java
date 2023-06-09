package com.dto;

import com.entity.HistoryAppointment;
import com.entity.MedicalAppointment;

public class MedicalAppointmentNotDoctorDTO extends MedicalAppointmentDTO {
	public MedicalAppointmentNotDoctorDTO(MedicalAppointment medicalAppointment) {
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
		setPatient(new UserNotRelationDTO(medicalAppointment.getPatient()));
		
		HistoryAppointment historyAppointment = medicalAppointment.getHistoryAppointment();
		setHistoryAppointment(historyAppointment != null ? new HistoryAppointmentNotRelationDTO(historyAppointment) : null);
	}
}
