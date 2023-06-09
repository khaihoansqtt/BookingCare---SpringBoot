package com.dto.doctor;

import java.util.List;

import com.entity.Schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddDoctorScheduleRes {
	private String message;
	private int doctorId;
	private List<Schedule> schedules;
}
