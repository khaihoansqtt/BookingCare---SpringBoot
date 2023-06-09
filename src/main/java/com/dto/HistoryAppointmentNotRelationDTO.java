package com.dto;


import com.entity.HistoryAppointment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoryAppointmentNotRelationDTO extends HistoryAppointmentDTO {
	public HistoryAppointmentNotRelationDTO(HistoryAppointment historyAppointment) {
		setId(historyAppointment.getId());
		setBasicPathological(historyAppointment.getBasicPathological());
		setPathologicalDetails(historyAppointment.getPathologicalDetails());
		setMedicalAppointmentId(historyAppointment.getMedicalAppointment().getId());
	}
}
