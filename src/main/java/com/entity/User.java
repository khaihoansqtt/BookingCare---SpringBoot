package com.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

	@Column(name = "avatar")
	private String avatar;

	@Column(name = "gender")
	private String gender;

	@Column(name = "description")
	private String description;

	@Column(name = "is_active")
	private int isActive;
	
	@Column(name = "block_reason")
	private String blockReason;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "role_id")
	private Role role;
	
	@OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private Doctor doctor;
	
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
	private List<MedicalAppointment> medicalAppointments;
	
	public User(UserDTO userDTO) {
		id = userDTO.getId();
		email = userDTO.getEmail();
		password = userDTO.getPassword();
		name = userDTO.getName();
		address = userDTO.getAddress();
		phone = userDTO.getPhone();
		avatar = userDTO.getAvatar();
		gender = userDTO.getGender();
		description = userDTO.getDescription();
		isActive = userDTO.getIsActive();
	}
}
