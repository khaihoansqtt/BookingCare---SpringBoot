package com.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.entity.Clinic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClinicDTO {
	
	private int id;
	private String name;
	private String address;
	private String phone;
	private String introductionHTML;
	private String introductionMarkdown;
	private String description;
	private String image;
	private List<DoctorDTO> doctorsDTO;
	
	public ClinicDTO(Clinic clinic) { 
		id = clinic.getId();
		name = clinic.getName();
		address = clinic.getAddress();
		phone = clinic.getPhone();
		introductionHTML = clinic.getIntroductionHTML();
		introductionMarkdown = clinic.getIntroductionMarkdown();
		description = clinic.getDescription();
		image = clinic.getImage();
		doctorsDTO = clinic.getDoctors().stream().map(doctor -> new DoctorDTO(doctor)).collect(Collectors.toList());
	}
}
