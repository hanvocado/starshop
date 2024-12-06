package com.starshop.entities;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
	
	private UUID id;
	private String userName;
	private String phoneNumber;
	private String email;
	private int defaultAddressId;
	private String profileImg;
	private int roleId;
}
