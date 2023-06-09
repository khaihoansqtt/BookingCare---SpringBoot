package com.dto.admin;

import lombok.Data;

@Data
public class BlockUserReq {
	private int userId;
	private String blockReason;
}
