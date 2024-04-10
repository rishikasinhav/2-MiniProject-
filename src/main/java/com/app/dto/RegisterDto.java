package com.app.dto;

import lombok.Data;

@Data
public class RegisterDto {
	
	private String name;
	
	private String email;
	
	private Long phone;
	
	private String pwd;
	
	private String updatedPwd = "NO";
	
	private String country;
	
	private String state;
	
	private String city;

}
