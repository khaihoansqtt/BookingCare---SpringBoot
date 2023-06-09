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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dto.DoctorDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="doctors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "info")
	private String info;

	@Column(name = "training")
	private String training;

	@Column(name = "achievement")
	private String achievement;

	@Column(name = "price")
	private int price;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "clinic_id")
	private Clinic clinic;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "specialty_id")
	private Specialty specialty;
	
	@ManyToMany(fetch = FetchType.LAZY,
				cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name = "doctor_schedule",
				joinColumns = @JoinColumn(name = "doctor_id"),
				inverseJoinColumns = @JoinColumn(name = "schedule_id"))
	private List<Schedule> schedules;
	
	@ManyToMany(fetch = FetchType.LAZY,
				cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name = "doctor_category",
				joinColumns = @JoinColumn(name = "doctor_id"),
				inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;
	
	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
	private List<MedicalAppointment> medicalAppointments;
	
	public Doctor(DoctorDTO doctorDTO) {
		info = doctorDTO.getInfo();
		training = doctorDTO.getInfo();
		achievement = doctorDTO.getInfo();
	}
}
