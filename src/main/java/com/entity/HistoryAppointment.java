package com.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dto.HistoryAppointmentDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="history_appointments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HistoryAppointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "basic_pathological")
	private String basicPathological;

	@Column(name = "pathological_details")
	private String pathologicalDetails;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "medical_appointment_id")
	private MedicalAppointment medicalAppointment;
	
	public HistoryAppointment(HistoryAppointmentDTO historyAppointmentDTO) {
		basicPathological = historyAppointmentDTO.getBasicPathological();
		pathologicalDetails = historyAppointmentDTO.getPathologicalDetails();
		
	}
}
