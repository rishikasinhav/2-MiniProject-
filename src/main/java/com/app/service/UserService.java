package com.app.service;

import java.util.List;
import java.util.Map;

import com.app.dto.LoginDto;
import com.app.dto.RegisterDto;
import com.app.dto.ResetPwdDto;
import com.app.dto.UserDto;
import com.app.entities.CityEntity;
import com.app.entities.CountryEntity;
import com.app.entities.StateEntity;
import com.app.entities.UserDtlsEntity;

public interface UserService {

	public UserDto getUser(UserDto userDto);
	
	public boolean registerUser(UserDto userDto);
	
	public boolean resetPwd(ResetPwdDto rDto);
	
	public List<CountryEntity> getCountry();
	
	public List<StateEntity> getState(Integer cid);
	
	public List<CityEntity> getCity(Integer sid);
	
	public String getquote();
	
	//public UserDto getUser(String email);
	
	
}
