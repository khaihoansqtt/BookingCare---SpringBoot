package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.Doctor;


public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	
	@Query("select d from Doctor d join d.specialty s where s.name like %?1%")
	List<Doctor> searchBySpecialty(String keySearch);
	
	@Query("select d from Doctor d join d.clinic c where c.address like %?1%")
	List<Doctor> searchByAddress(String keySearch);
	
	@Query("select distinct d from Doctor d join d.categories c where c.name like %?1%")
	List<Doctor> searchByCategory(String keySearch);
	
	@Query("select d from Doctor d where d.price >= ?1 and d.price <= ?2")
	List<Doctor> searchByPrice(Integer min, Integer max);
}
