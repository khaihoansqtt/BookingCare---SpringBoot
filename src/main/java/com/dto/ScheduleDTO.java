package com.dto;

import com.entity.Schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScheduleDTO {
	private int id;
	private String time;
	
	public ScheduleDTO(Schedule schedule) {
		id = schedule.getId();
		time = schedule.getTime();
	}
}
