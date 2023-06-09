package com.dto;

import com.entity.MedicalAppointment;

public class MedicalAppointmentNotHistoryDTO extends MedicalAppointmentDTO {
	public MedicalAppointmentNotHistoryDTO(MedicalAppointment medicalAppointment) {
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
		setPatient(new UserNotRelationDTO(medicalAppointment.getPatient()));
	}
}
