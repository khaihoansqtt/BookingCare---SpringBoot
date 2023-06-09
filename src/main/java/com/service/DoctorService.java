package com.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dto.doctor.AddDoctorCategoryRes;
import com.dto.doctor.AddDoctorScheduleRes;
import com.dto.doctor.MailToPatientReq;
import com.dto.doctor.MailToPatientRes;
import com.entity.Category;
import com.entity.Doctor;
import com.entity.Schedule;
import com.entity.User;
import com.repository.CategoryRepository;
import com.repository.DoctorRepository;
import com.repository.ScheduleRepository;
import com.repository.UserRepository;

@Service
@Transactional
public class DoctorService {

	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	DoctorRepository doctorRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	JavaMailSender mailSender;

	// Thêm schedule khám bệnh cho bác sĩ
	public AddDoctorScheduleRes addDoctorSchedule(int userId, int[] scheduleIdArr) {
		List<Schedule> schedules = new ArrayList<>();
		for (int i : scheduleIdArr) {
			Schedule schedule = scheduleRepository.findById(i).get();
			schedules.add(schedule);
		}
		Doctor doctor = getDoctorFromUserId(userId);
		doctor.setSchedules(schedules);
		doctorRepository.save(doctor);
		return new AddDoctorScheduleRes("add schedules successfully", doctor.getId(), schedules);
	}

	// Thêm category cho bác sĩ
	public AddDoctorCategoryRes addDoctorCategory(int userId, int[] categoryIdArr) {
		List<Category> categories = new ArrayList<>();
		for (int i : categoryIdArr) {
			Category category = categoryRepository.findById(i).get();
			categories.add(category);
		}
		Doctor doctor = getDoctorFromUserId(userId);
		doctor.setCategories(categories);
		doctorRepository.save(doctor);
		return new AddDoctorCategoryRes("add categories successfully", doctor.getId(), categories);
	}

	// Bác sĩ gửi email cho nạn nhân kèm theo file 
	public MailToPatientRes sendEmailToPatient(MailToPatientReq mailToPatientReq) throws MessagingException {
		int patientId = mailToPatientReq.getPatientId();
		
		String patientEmail = userRepository.findById(patientId).get().getEmail();
		String subject = mailToPatientReq.getSubject();
		String text = mailToPatientReq.getText();
		MultipartFile file = mailToPatientReq.getFile();
		
		sendMailWithAttachment("khaihoancr7@gmail.com", subject, text, file);
		return new MailToPatientRes(patientId, subject, text, file.getOriginalFilename());
	}
	// Function gửi mail
	public void sendMailWithAttachment(String to, String subject, String text, MultipartFile fileToAttach) throws MessagingException {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			helper.addAttachment(fileToAttach.getOriginalFilename(), new InputStreamSource() {

				@Override
				public InputStream getInputStream() throws IOException {
					return fileToAttach.getInputStream();
				}
			}, fileToAttach.getContentType());

			mailSender.send(message);
	}

	public Doctor getDoctorFromUserId(int userId) {
		User user = userRepository.findById(userId).get();
		return user.getDoctor();
	}
}
