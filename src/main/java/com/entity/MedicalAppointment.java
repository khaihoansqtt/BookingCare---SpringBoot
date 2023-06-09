package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dto.MedicalAppointmentDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="medical_appointments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MedicalAppointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "time")
	private String time;

	@Column(name = "price")
	private String price;

	@Column(name = "name")
	private String name;

	@Column(name = "gender")
	private String gender;

	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
	private String address;

	@Column(name = "reason")
	private String reason;

	@Column(name = "is_confirmed")
	private int isConfirmed;

	@Column(name = "cancel_reason")
	private String cancelReason;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "patient_id")
	private User patient;
	
	@OneToOne(mappedBy = "medicalAppointment", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private HistoryAppointment historyAppointment;
	
	public MedicalAppointment(MedicalAppointmentDTO medicalAppointmentDTO) {
		time = medicalAppointmentDTO.getTime();
		price = medicalAppointmentDTO.getPrice();
		name = medicalAppointmentDTO.getName();
		gender = medicalAppointmentDTO.getGender();
		phone = medicalAppointmentDTO.getPhone();
		address = medicalAppointmentDTO.getAddress();
		reason = medicalAppointmentDTO.getReason();
	}
}
