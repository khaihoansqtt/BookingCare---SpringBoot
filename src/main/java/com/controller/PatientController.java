package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.config.CustomUserDetails;
import com.dto.MedicalAppointmentDTO;
import com.dto.MedicalAppointmentOnlyDoctorDTO;
import com.dto.UserDTO;
import com.service.MedicalAppointmentService;
import com.service.UserService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

	@Autowired
	UserService userService;
	@Autowired
	MedicalAppointmentService medicalAppointmentService;
	
	@GetMapping("/profile")
	public UserDTO getProfile(Authentication authentication) {
		int userId = getUserId(authentication);
		return userService.getProfile(userId);
	}
	
	@PostMapping("/bookDoctor")
	public MedicalAppointmentOnlyDoctorDTO bookDoctor(@RequestBody MedicalAppointmentDTO medicalAppointmentDTO,
										Authentication authentication) {
		int patientId = getUserId(authentication);
		return medicalAppointmentService.bookDoctor(patientId, medicalAppointmentDTO);
	}
	
	// Lấy userId của phiên đăng nhập
	public int getUserId(Authentication authentication) {
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		return userDetails.getId();
	}
}
