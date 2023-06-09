package com.dto;

import com.entity.Specialty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecialtyNotRelationDTO extends SpecialtyDTO {
	
	public SpecialtyNotRelationDTO(Specialty specialty) {
		setId(specialty.getId());
		setName(specialty.getName());
		setDescription(specialty.getDescription());
		setImage(specialty.getImage());
	}
}
