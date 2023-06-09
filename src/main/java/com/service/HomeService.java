package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.ClinicNotRelationDTO;
import com.dto.DoctorNotUserDTO;
import com.dto.SpecialtyNotRelationDTO;
import com.entity.Clinic;
import com.entity.Doctor;
import com.entity.Specialty;
import com.repository.ClinicRepository;
import com.repository.DoctorRepository;
import com.repository.SpecialtyRepository;

@Service
@Transactional
public class HomeService {

	@Autowired
	SpecialtyRepository specialtyRepository;
	@Autowired
	ClinicRepository clinicRepository;
	@Autowired
	DoctorRepository doctorRepository;
	
	public List<SpecialtyNotRelationDTO> getTopSpecialties() {
		List<Specialty> specialties = specialtyRepository.getTopSpecialties();
		return specialties.stream().map(specialty -> new SpecialtyNotRelationDTO(specialty))
									.collect(Collectors.toList());
	}
	
	public List<ClinicNotRelationDTO> getTopClinics() {
		List<Clinic> clinics = clinicRepository.getTopClinics();
		return clinics.stream().map(clinic -> new ClinicNotRelationDTO(clinic))
									.collect(Collectors.toList());
	}
	
	public List<DoctorNotUserDTO> search(String keySearch, String by, Integer min, Integer max) {
		List<Doctor> doctors = null;
		switch (by) {
			case "specialty":
				doctors = doctorRepository.searchBySpecialty(keySearch);
				break;
			case "address":
				doctors = doctorRepository.searchByAddress(keySearch);
				break;
			case "category":
				doctors = doctorRepository.searchByCategory(keySearch);
				break;
			case "price":
				doctors = doctorRepository.searchByPrice(min, max);
				break;
		}
		return doctors.stream().map(doctor -> new DoctorNotUserDTO(doctor)).collect(Collectors.toList());
	}
	
	public List<ClinicNotRelationDTO> searchByClinic(String keySearch) {
		List<Clinic> clinics = clinicRepository.searchByName(keySearch);
		return clinics.stream().map(clinic -> new ClinicNotRelationDTO(clinic)).collect(Collectors.toList());
	}
}
