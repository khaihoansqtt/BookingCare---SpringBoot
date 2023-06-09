package com.dto.admin;

import com.dto.DoctorDTO;
import com.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AddDoctorReqDTO {
	private UserDTO userDTO;
	private DoctorDTO doctorDTO;
}
