package com.dto;

import com.entity.Clinic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicNotRelationDTO extends ClinicDTO {
	
	public ClinicNotRelationDTO(Clinic clinic) {
		setId(clinic.getId());
		setName(clinic.getName());
		setAddress(clinic.getAddress());
		setPhone(clinic.getPhone());
		setIntroductionHTML(clinic.getIntroductionHTML());
		setIntroductionMarkdown(clinic.getIntroductionMarkdown());
		setDescription(clinic.getDescription());
		setImage(clinic.getImage());
	}
}
