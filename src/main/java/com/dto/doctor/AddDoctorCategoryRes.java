package com.dto.doctor;

import java.util.List;

import com.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddDoctorCategoryRes {
	private String message;
	private int doctorId;
	private List<Category> categories;
}
