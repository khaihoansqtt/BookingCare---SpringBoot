package com.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.config.CustomUserDetails;
import com.dto.HistoryAppointmentDTO;
import com.dto.HistoryAppointmentNotRelationDTO;
import com.dto.MedicalAppointmentOnlyHistoryDTO;
import com.dto.MedicalAppointmentOnlyPatientDTO;
import com.dto.doctor.AddDoctorCategoryReq;
import com.dto.doctor.AddDoctorCategoryRes;
import com.dto.doctor.AddDoctorScheduleReq;
import com.dto.doctor.AddDoctorScheduleRes;
import com.dto.doctor.ConfirmMedicalAppointmentReq;
import com.dto.doctor.ConfirmMedicalAppointmentRes;
import com.dto.doctor.MailToPatientReq;
import com.dto.doctor.MailToPatientRes;
import com.service.DoctorService;
import com.service.MedicalAppointmentService;
import com.service.UserService;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

	@Autowired
	UserService userService;
	@Autowired
	DoctorService doctorService;
	@Autowired
	MedicalAppointmentService medicalAppointmentService;
	
	// Tạo schedule cho bác sĩ
	@PostMapping("/add-schedule")
	public AddDoctorScheduleRes addDoctorSchedule(@RequestBody AddDoctorScheduleReq body, Authentication authentication) {
		int userId = getUserId(authentication);
		int[] scheduleIds = body.getScheduleIds();
		return doctorService.addDoctorSchedule(userId, scheduleIds);
	}
	
	// Tạo category cho bác sĩ
	@PostMapping("/add-category")
	public AddDoctorCategoryRes addDoctorCategory(@RequestBody AddDoctorCategoryReq body, Authentication authentication) {
		int userId = getUserId(authentication);
		int[] categoryIds = body.getCategoryIds();
		return doctorService.addDoctorCategory(userId, categoryIds);
	}
	
	// Xem danh sách bệnh nhân đã đặt lịch của bác sĩ
	@GetMapping("/registerd-patient-list")
	public List<MedicalAppointmentOnlyPatientDTO> getPatientRegisterdListOfDoctor(Authentication authentication) {
		int userId = getUserId(authentication);
		return medicalAppointmentService.getPatientRegisterdListOfDoctor(userId);
	}
	
	// Xem danh sách bệnh nhân đã khám cùng với bệnh án
	@GetMapping("/patient-list")
	public List<MedicalAppointmentOnlyHistoryDTO> getPatientListOfDoctor(Authentication authentication) {
		int userId = getUserId(authentication);
		return medicalAppointmentService.getPatientListOfDoctor(userId);
	}
	
	// Xác nhận lịch khám của bệnh nhân
	@PostMapping("/confirm-medical-appoitment")
	public ConfirmMedicalAppointmentRes confirmMedicalAppointment(@RequestBody ConfirmMedicalAppointmentReq body) {
		int medicalAppointmentId = body.getMedicalAppointmentId();
		
		return medicalAppointmentService.confirmMedicalAppointment(medicalAppointmentId);
	}
	
	// Hủy lịch khám của bệnh nhân
	@PostMapping("/cancel-confirm-medical-appoitment")
	public ConfirmMedicalAppointmentRes cancelConfirmMedicalAppointment(@RequestBody ConfirmMedicalAppointmentReq body) {
		int medicalAppointmentId = body.getMedicalAppointmentId();
		String cancelReason = body.getCancelReason();
		
		return medicalAppointmentService.cancelConfirmMedicalAppointment(medicalAppointmentId, cancelReason);
	}
	
	// Bác sĩ lưu lại bệnh án khi khám xong (lưu vào bảng history_appointment)
	@PostMapping("/save-history-appointment")
	public HistoryAppointmentNotRelationDTO saveHistoryAppointment(@RequestBody HistoryAppointmentDTO historyAppointmentDTO) {
		return medicalAppointmentService.saveHistoryAppointment(historyAppointmentDTO);
	}
	
	// Bác sĩ gửi email cho bệnh nhân, có thể gửi kèm theo file
	@PostMapping(value = "/send-email-to-patient", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public MailToPatientRes sendEmailToPatient(@ModelAttribute MailToPatientReq req) throws MessagingException {
		return doctorService.sendEmailToPatient(req);
	}
	
	// Lấy userId của phiên đăng nhập
	public int getUserId(Authentication authentication) {
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		return userDetails.getId();
	}
	
}
