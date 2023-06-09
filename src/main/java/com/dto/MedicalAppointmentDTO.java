package com.dto;

import com.entity.MedicalAppointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MedicalAppointmentDTO {
	private int id;
	private int scheduleId;
	private String time;
	private String price;
	private String name;
	private String gender;
	private String phone;
	private String address;
	private String reason;
	private int isConfirmed;
	private String cancelReason;
	private int doctorId;
	private int patientId;
	private DoctorDTO doctor;
	private UserDTO patient;
	private HistoryAppointmentDTO historyAppointment;
	
	public MedicalAppointmentDTO(MedicalAppointment medicalAppointment) {
		id = medicalAppointment.getId();
		time = medicalAppointment.getTime();
		price = medicalAppointment.getPrice();
		name = medicalAppointment.getName();
		gender= medicalAppointment.getTime();
		phone = medicalAppointment.getTime();
		address = medicalAppointment.getTime();
		reason = medicalAppointment.getReason();
		isConfirmed = medicalAppointment.getIsConfirmed();
		cancelReason = medicalAppointment.getCancelReason();
		doctor = new DoctorNotRelationDTO(medicalAppointment.getDoctor());
		patient = new UserNotRelationDTO(medicalAppointment.getPatient());
		historyAppointment = new HistoryAppointmentNotRelationDTO(medicalAppointment.getHistoryAppointment());
	}
}
