package com.dto;

import com.entity.MedicalAppointment;

public class MedicalAppointmentOnlyHistoryDTO extends MedicalAppointmentDTO {
	public MedicalAppointmentOnlyHistoryDTO(MedicalAppointment medicalAppointment) {
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
		setHistoryAppointment(new HistoryAppointmentNotRelationDTO(medicalAppointment.getHistoryAppointment()));
	}
}
