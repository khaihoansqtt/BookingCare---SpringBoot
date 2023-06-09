package com.dto;

import com.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotRelationDTO extends UserDTO {
	
	public UserNotRelationDTO(User user) {
		setId(user.getId());
		setEmail(user.getEmail());
		setName(user.getName());
		setAddress(user.getAddress());
		setPhone(user.getPhone());
		setAvatar(user.getAvatar());
		setGender(user.getGender());
		setDescription(user.getDescription());
		setIsActive(user.getIsActive());
		setRoleId(user.getRole().getId());
		setRoleName(user.getRole().getName());
	}
}
