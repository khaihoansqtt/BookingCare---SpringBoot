package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ClinicNotRelationDTO;
import com.dto.DoctorNotUserDTO;
import com.dto.SpecialtyNotRelationDTO;
import com.service.HomeService;

@RestController
@RequestMapping("/api")
public class HomeController {
	@Autowired
	HomeService homeService;
	
	@GetMapping("/public/top-specialties")
	public List<SpecialtyNotRelationDTO> getTopSpecialties() {
		return homeService.getTopSpecialties();
	}
	
	@GetMapping("/public/top-clinics")
	public List<ClinicNotRelationDTO> getTopClinics() {
		return homeService.getTopClinics();
	}
	
	@GetMapping("/search")
	public List<DoctorNotUserDTO> search(@RequestParam(value = "keySearch", required = false) String keySearch,
										@RequestParam("by") String by,
										@RequestParam(value = "min", required = false) Integer min,
										@RequestParam(value = "max", required = false) Integer max) {
		return homeService.search(keySearch, by, min, max);
	}
	
	@GetMapping("/search/by-clinic")
	public List<ClinicNotRelationDTO> searchByClinic(@RequestParam(value = "keySearch", required = false) String keySearch) {
		return homeService.searchByClinic(keySearch);
	}
}
