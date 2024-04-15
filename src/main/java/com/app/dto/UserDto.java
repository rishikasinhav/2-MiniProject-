package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserDto {

	private String name;
	
	private String email;
	
	private String phone;
	
	private Integer countryId;
	
	private Integer stateId;
	
	private Integer cityId;
	
	private String pwd;
	
	private String updatedPwd;
	
	}
