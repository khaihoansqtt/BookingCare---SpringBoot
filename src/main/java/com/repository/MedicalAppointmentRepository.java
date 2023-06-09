package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.MedicalAppointment;
import com.entity.User;
import com.entity.Doctor;




public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Integer> {

	List<MedicalAppointment> findByPatient(User patient);
	List<MedicalAppointment> findByDoctor(Doctor doctor);
	
	@Query("select ma from MedicalAppointment ma join ma.historyAppointment ha where ma.doctor.id = ?1")
	List<MedicalAppointment> findFinishedByDoctorId(int doctorId);
	
	
}
