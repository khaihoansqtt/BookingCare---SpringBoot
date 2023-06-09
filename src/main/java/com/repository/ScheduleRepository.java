package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Schedule;


public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
