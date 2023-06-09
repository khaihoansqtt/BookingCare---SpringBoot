package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.Clinic;


public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
	
	@Query("select c, count(*) as numberOfMa from Doctor d right join d.clinic c"
			+ " left join MedicalAppointment ma on d.id = ma.doctor.id"
			+ " group by c.id order by numberOfMa desc")
	List<Clinic> getTopClinics();
	
	
	@Query("select c from Clinic c where c.name like %?1%")
	List<Clinic> searchByName(String keySearch);
}
