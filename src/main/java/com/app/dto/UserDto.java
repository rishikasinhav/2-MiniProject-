package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class UserDto {

	private String u_name;
	
	private String u_email;
	
	private String u_phone;
	
	private String u_country;
	
	private String u_state;
	
	private String u_city;
	
	private String u_pwd;
	
	private String u_updatedPwd;
	
	private String u_confirmPwd;
}
