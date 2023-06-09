package com.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dto.DoctorDTO;
import com.dto.UserDTO;
import com.dto.admin.AddDoctorReqDTO;
import com.dto.admin.BlockUserRes;
import com.dto.admin.UnblockUserRes;
import com.entity.Clinic;
import com.entity.Doctor;
import com.entity.Role;
import com.entity.Specialty;
import com.entity.User;
import com.repository.CategoryRepository;
import com.repository.ClinicRepository;
import com.repository.DoctorRepository;
import com.repository.MedicalAppointmentRepository;
import com.repository.RoleRepository;
import com.repository.ScheduleRepository;
import com.repository.SpecialtyRepository;
import com.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	PasswordEncoder bcryptEncoder;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	DoctorRepository doctorRepository;
	@Autowired
	ClinicRepository clinicRepository;
	@Autowired
	SpecialtyRepository specialtyRepository;
	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	MedicalAppointmentRepository medicalAppointmentRepository;
	
	// Thêm tài khoản bệnh nhân
	public UserDTO addNewUser(UserDTO userDTO) {
        return new UserDTO(addUser(userDTO));
    }
	
	public User addUser(UserDTO userDTO) {
		Role role = roleRepository.findById(3).get();	// roleId = 3 là ROLE_PATIENT
		
        User newUser = new User(userDTO);
        newUser.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
        newUser.setIsActive(1);
        newUser.setRole(role);	
        return userRepository.save(newUser);
    }
	
	// Thêm tài khoản bác sĩ
	public DoctorDTO addNewDoctor(AddDoctorReqDTO addDoctorReqDTO) {
		DoctorDTO doctorDTO = addDoctorReqDTO.getDoctorDTO();
		User user = addUser(addDoctorReqDTO.getUserDTO());
		
		Clinic clinic = clinicRepository.findById(doctorDTO.getClinicId()).get();
		Specialty specialty = specialtyRepository.findById(doctorDTO.getSpecialtyId()).get();
		
		Doctor doctor = new Doctor(doctorDTO);
		doctor.setClinic(clinic);
		doctor.setSpecialty(specialty);
		doctor.setUser(user);
		
		return new DoctorDTO(doctorRepository.save(doctor));
	}
	
	// Xem thông tin của một tài khoản bệnh nhân
	public UserDTO getProfile(int userId) {
		User user = userRepository.findById(userId).get();
		return new UserDTO(user);
	}
	
	// Khóa một tài khoản
	public BlockUserRes blockUser(int userId, String blockReason) {
		User user = userRepository.findById(userId).get();
		if (user.getIsActive() == 0) {
			user.setBlockReason(blockReason);
			return new BlockUserRes(userId, blockReason, "user has been blocked before");
		}
		
		user.setIsActive(0);
		user.setBlockReason(blockReason);
		userRepository.save(user);
		
		return new BlockUserRes(userId, blockReason, "blocks user successfully");
	}
	
	// Mở khóa một tài khoản
	public UnblockUserRes unblockUser(int userId) {
		User user = userRepository.findById(userId).get();
		if (user.getIsActive() == 1) {
			return new UnblockUserRes(userId,  "user has not been blocked before");
		}
		
		user.setIsActive(1);
		user.setBlockReason(null);
		userRepository.save(user);
		return new UnblockUserRes(userId, "unblock user successfully");
	}
}
