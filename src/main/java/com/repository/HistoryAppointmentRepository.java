package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.HistoryAppointment;
import com.entity.MedicalAppointment;


public interface HistoryAppointmentRepository extends JpaRepository<HistoryAppointment, Integer> {
}
