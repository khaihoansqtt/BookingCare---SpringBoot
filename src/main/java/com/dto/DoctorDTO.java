package com.dto;

import com.entity.Doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoctorDTO {
	private int id;
	private String info;
	private String training;
	private String achievement;
	private int price;
	private int clinicId;
	private int specialtyId;
	private ClinicDTO clinicDTO;
	private SpecialtyDTO specialtyDTO;
	private UserDTO userDTO;
	
	public DoctorDTO(Doctor doctor) {
		id = doctor.getId();
		info = doctor.getInfo();
		training = doctor.getTraining();
		achievement = doctor.getAchievement();
		price = doctor.getPrice();
		clinicId = doctor.getClinic().getId();
		specialtyId = doctor.getSpecialty().getId();
		clinicDTO = new ClinicNotRelationDTO(doctor.getClinic());
		specialtyDTO =new SpecialtyNotRelationDTO(doctor.getSpecialty());
		userDTO =new UserNotRelationDTO(doctor.getUser());
	}
}
