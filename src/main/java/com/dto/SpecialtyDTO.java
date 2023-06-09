package com.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.entity.Doctor;
import com.entity.Specialty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpecialtyDTO {

	private int id;
	private String name;
	private String description;
	private String image;
	private List<DoctorDTO> doctors;

	public SpecialtyDTO(Specialty specialty) {
		id = specialty.getId();
		name = specialty.getName();
		description = specialty.getDescription();
		image = specialty.getImage();
		doctors = specialty.getDoctors().stream()
										.map(doctor -> new DoctorNotRelationDTO(doctor))
										.collect(Collectors.toList());
	}
}
