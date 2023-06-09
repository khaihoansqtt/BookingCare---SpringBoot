package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.DoctorDTO;
import com.dto.MedicalAppointmentNotDoctorDTO;
import com.dto.MedicalAppointmentNotPatientDTO;
import com.dto.admin.AddDoctorReqDTO;
import com.dto.admin.BlockUserReq;
import com.dto.admin.BlockUserRes;
import com.dto.admin.UnblockUserRes;
import com.service.MedicalAppointmentService;
import com.service.UserService;

@RestController
@RequestMapping("/api/systems")
public class AdminController {
	
	@Autowired
	UserService userService;
	@Autowired
	MedicalAppointmentService medicalAppointmentService;
	
	
	@PostMapping("/add-new-doctor")
	public DoctorDTO addNewDoctor(@RequestBody AddDoctorReqDTO addDoctorReqDTO) {
		return userService.addNewDoctor(addDoctorReqDTO);
	}
	
	@PostMapping("/block-user")
	public BlockUserRes blockUser(@RequestBody BlockUserReq body) {
		int userId = body.getUserId();
		String blockReason = body.getBlockReason();
		return userService.blockUser(userId, blockReason);
	}
	
	@PostMapping("/unblock-user")
	public UnblockUserRes unblockUser(@RequestBody BlockUserReq body) {
		return userService.unblockUser(body.getUserId());
	}
	
	@GetMapping("/patient-appointment-detail")
	public List<MedicalAppointmentNotPatientDTO> getPatientAppointmentDetail(@RequestParam("patientId") int patientId) {
		return medicalAppointmentService.getPatientAppointmentDetail(patientId);
	}
	
	@GetMapping("/doctor-appointment-detail")
	public List<MedicalAppointmentNotDoctorDTO> getDoctorAppointmentDetail(@RequestParam("doctorId") int doctorId) {
		return medicalAppointmentService.getDoctorAppointmentDetail(doctorId);
	}
}