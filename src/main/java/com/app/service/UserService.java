package com.app.service;

import java.util.Map;

import com.app.dto.LoginDto;
import com.app.dto.RegisterDto;
import com.app.dto.ResetPwdDto;
import com.app.dto.UserDto;
import com.app.entities.UserDtlsEntity;

public interface UserService {

	public UserDto getUser(LoginDto loginDto);
	
	public boolean registerUser(RegisterDto rDto);
	
	public boolean resetPwd(ResetPwdDto pwdDto);
	
	public Map<Integer, String> getCountry();
	
	public Map<Integer,String> getState(Integer cid);
	
	public Map<Integer, String> getCity(Integer sid);
	
	public String getquote();
	
	public UserDto getUser(String email);
	
	
}
