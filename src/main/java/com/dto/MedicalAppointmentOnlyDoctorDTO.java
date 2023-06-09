package com.dto;

import com.entity.MedicalAppointment;

public class MedicalAppointmentOnlyDoctorDTO extends MedicalAppointmentDTO {
	public MedicalAppointmentOnlyDoctorDTO(MedicalAppointment medicalAppointment) {
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
	}
}
