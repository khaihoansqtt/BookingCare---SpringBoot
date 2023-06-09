package com.dto;

import com.entity.Doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class DoctorNotUserDTO extends DoctorDTO {
	
	public DoctorNotUserDTO(Doctor doctor) {
		setId(doctor.getId());
		setInfo(doctor.getInfo());
		setTraining(doctor.getTraining());
		setAchievement(doctor.getAchievement());
		setPrice(doctor.getPrice());
		setClinicId(doctor.getClinic().getId());
		setSpecialtyId(doctor.getSpecialty().getId());
		setClinicDTO(new ClinicNotRelationDTO(doctor.getClinic()));
		setSpecialtyDTO(new SpecialtyNotRelationDTO(doctor.getSpecialty()));
	}
}
