package com.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
	private int id;
	private String email;
	private String password;
	private String name;
	private String address;
	private String phone;
	private String avatar;
	private String gender;
	private String description;
	private int isActive;
	private int roleId;
	private String roleName;
	private List<MedicalAppointmentDTO> medicalAppointmentsDTO;
	
	public UserDTO(User user) {
		id = user.getId();
		email = user.getEmail();
		name = user.getName();
		address = user.getAddress();
		phone = user.getPhone();
		avatar = user.getAvatar();
		gender = user.getGender();
		description = user.getDescription();
		isActive = user.getIsActive();
		roleId = user.getRole().getId();
		roleName = user.getRole().getName();
		
		if (user.getMedicalAppointments() != null) {
			medicalAppointmentsDTO = user.getMedicalAppointments().stream()
					.map(medicalappointment -> new MedicalAppointmentOnlyDoctorDTO(medicalappointment))
					.collect(Collectors.toList());
		} else {
			medicalAppointmentsDTO = null;
		}
	}
}
