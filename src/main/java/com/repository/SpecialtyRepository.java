package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.Specialty;


public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {
	
	@Query("select s, count(*) as numberOfMa from Doctor d right join d.specialty s"
			+ " left join MedicalAppointment ma on d.id = ma.doctor.id"
			+ " group by s.id order by numberOfMa desc")
	List<Specialty> getTopSpecialties();
}
