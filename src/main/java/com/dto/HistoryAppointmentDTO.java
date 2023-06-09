package com.dto;


import com.entity.HistoryAppointment;
import com.entity.MedicalAppointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HistoryAppointmentDTO {
	private int id;
	private String basicPathological;
	private String pathologicalDetails;
	private int medicalAppointmentId;
	private MedicalAppointmentDTO medicalAppointment;
	
	public HistoryAppointmentDTO(HistoryAppointment historyAppointment) {
		id = historyAppointment.getId();
		basicPathological = historyAppointment.getBasicPathological();
		pathologicalDetails = historyAppointment.getPathologicalDetails();
		medicalAppointment = new MedicalAppointmentNotRelationDTO(historyAppointment.getMedicalAppointment());
	}
}
