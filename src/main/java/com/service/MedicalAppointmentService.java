package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.HistoryAppointmentDTO;
import com.dto.HistoryAppointmentNotRelationDTO;
import com.dto.MedicalAppointmentDTO;
import com.dto.MedicalAppointmentNotDoctorDTO;
import com.dto.MedicalAppointmentNotPatientDTO;
import com.dto.MedicalAppointmentOnlyDoctorDTO;
import com.dto.MedicalAppointmentOnlyHistoryDTO;
import com.dto.MedicalAppointmentOnlyPatientDTO;
import com.dto.doctor.ConfirmMedicalAppointmentRes;
import com.entity.Doctor;
import com.entity.HistoryAppointment;
import com.entity.MedicalAppointment;
import com.entity.Schedule;
import com.entity.User;
import com.repository.DoctorRepository;
import com.repository.HistoryAppointmentRepository;
import com.repository.MedicalAppointmentRepository;
import com.repository.ScheduleRepository;
import com.repository.UserRepository;

@Service
@Transactional
public class MedicalAppointmentService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	DoctorRepository doctorRepository;
	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	MedicalAppointmentRepository medicalAppointmentRepository;
	@Autowired
	HistoryAppointmentRepository historyAppointmentRepository;

	// Bệnh nhân book bác sĩ
	public MedicalAppointmentOnlyDoctorDTO bookDoctor(int patientId, MedicalAppointmentDTO medicalAppointmentDTO) {
		User patient = userRepository.findById(patientId).get();
		Doctor doctor = doctorRepository.findById(medicalAppointmentDTO.getDoctorId()).get();
		Schedule schedule = scheduleRepository.findById(medicalAppointmentDTO.getScheduleId()).get();

		MedicalAppointment medicalAppointment = new MedicalAppointment(medicalAppointmentDTO);
		medicalAppointment.setPatient(patient);
		medicalAppointment.setDoctor(doctor);
		medicalAppointment.setTime(schedule.getTime());

		medicalAppointmentRepository.save(medicalAppointment);

		MedicalAppointmentOnlyDoctorDTO dtoAfterSave = new MedicalAppointmentOnlyDoctorDTO(medicalAppointment);
		dtoAfterSave.setDoctorId(doctor.getId());
		dtoAfterSave.setPatientId(patientId);
		return dtoAfterSave;
	}

	// Bác sĩ lấy danh sách bệnh nhân đã đặt lịch khám
	public List<MedicalAppointmentOnlyPatientDTO> getPatientRegisterdListOfDoctor(int userId) {
		Doctor doctor = getDoctorFromUserId(userId);
		List<MedicalAppointment> medicalAppointments = doctor.getMedicalAppointments();

		return medicalAppointments.stream()
				.map(medicalAppointment -> new MedicalAppointmentOnlyPatientDTO(medicalAppointment))
				.collect(Collectors.toList());
	}

	// Bác sĩ lấy danh sách bệnh nhân đã khám xong cùng với bệnh án
	public List<MedicalAppointmentOnlyHistoryDTO> getPatientListOfDoctor(int userId) {
		int doctorId = getDoctorFromUserId(userId).getId();
		List<MedicalAppointment> medicalAppointments = medicalAppointmentRepository.findFinishedByDoctorId(doctorId);
		
		for (MedicalAppointment medicalAppointment : medicalAppointments) {
			System.out.println(medicalAppointment.getId());
		}
		return medicalAppointments.stream()
				.map(medicalAppointment -> new MedicalAppointmentOnlyHistoryDTO(medicalAppointment))
				.collect(Collectors.toList());
	}

	// Bác sĩ XÁC NHẬN lịch khám bệnh của bệnh nhân
	public ConfirmMedicalAppointmentRes confirmMedicalAppointment(int medicalAppointmentId) {
		MedicalAppointment medicalAppointment = medicalAppointmentRepository.findById(medicalAppointmentId).get();
		medicalAppointment.setIsConfirmed(1); // 2 là trạng thái XÁC NHẬN

		medicalAppointmentRepository.save(medicalAppointment);
		return new ConfirmMedicalAppointmentRes("Confirm successfully",
				new MedicalAppointmentOnlyPatientDTO(medicalAppointment));
	}

	// Bác sĩ HỦY XÁC NHẬN lịch khám bệnh của bệnh nhân
	public ConfirmMedicalAppointmentRes cancelConfirmMedicalAppointment(int medicalAppointmentId, String cancelReason) {
		MedicalAppointment medicalAppointment = medicalAppointmentRepository.findById(medicalAppointmentId).get();
		medicalAppointment.setIsConfirmed(2); // 2 là trạng thái HỦY XÁC NHẬN
		medicalAppointment.setCancelReason(cancelReason);

		medicalAppointmentRepository.save(medicalAppointment);
		return new ConfirmMedicalAppointmentRes("Cancel confirm successfully",
				new MedicalAppointmentOnlyPatientDTO(medicalAppointment));
	}

	// Bác sĩ lưu bệnh án cho bệnh nhân sau khi khám xong
	public HistoryAppointmentNotRelationDTO saveHistoryAppointment(HistoryAppointmentDTO historyAppointmentDTO) {
		MedicalAppointment medicalAppointment = medicalAppointmentRepository
				.findById(historyAppointmentDTO.getMedicalAppointmentId()).get();
		HistoryAppointment historyAppointment = medicalAppointment.getHistoryAppointment();
		if (historyAppointment == null) {
			historyAppointment = new HistoryAppointment(historyAppointmentDTO);

			historyAppointment.setMedicalAppointment(medicalAppointment);
		} else {
			historyAppointment.setBasicPathological(historyAppointmentDTO.getBasicPathological());
			historyAppointment.setPathologicalDetails(historyAppointmentDTO.getPathologicalDetails());
		}
		HistoryAppointment historyAppointmentAfterSave = historyAppointmentRepository.save(historyAppointment);

		return new HistoryAppointmentNotRelationDTO(historyAppointmentAfterSave);
	}
	
	// Admin xem lịch khám của một bệnh nhân
	public List<MedicalAppointmentNotPatientDTO> getPatientAppointmentDetail(int patientId) {
		User patient = userRepository.findById(patientId).get();
		List<MedicalAppointment> medicalAppointments = medicalAppointmentRepository.findByPatient(patient);
		return medicalAppointments.stream()
				.map(medicalAppointment -> new MedicalAppointmentNotPatientDTO(medicalAppointment))
				.collect(Collectors.toList());
	}
	
	// Admin xem lịch khám của một bác sĩ
	public List<MedicalAppointmentNotDoctorDTO> getDoctorAppointmentDetail(int doctorId) {
		Doctor doctor = doctorRepository.findById(doctorId).get();
		List<MedicalAppointment> medicalAppointments = medicalAppointmentRepository.findByDoctor(doctor);
		return medicalAppointments.stream()
				.map(medicalAppointment -> new MedicalAppointmentNotDoctorDTO(medicalAppointment))
				.collect(Collectors.toList());
	}

	public Doctor getDoctorFromUserId(int userId) {
		User user = userRepository.findById(userId).get();
		return user.getDoctor();
	}
}
