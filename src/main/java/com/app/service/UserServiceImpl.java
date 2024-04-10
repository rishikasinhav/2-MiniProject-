package com.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.dto.LoginDto;
import com.app.dto.RegisterDto;
import com.app.dto.ResetPwdDto;
import com.app.dto.UserDto;
import com.app.entities.UserDtlsEntity;
import com.app.repo.CityRepo;
import com.app.repo.CountryRepo;
import com.app.repo.StateRepo;
import com.app.repo.UserDtlsRepo;

public class UserServiceImpl implements UserService {

	@Autowired
	private  UserDtlsRepo uRepo;
	
	@Autowired
	private CountryRepo cRepo;
	
	@Autowired
	private CityRepo ctyRepo;
	
	@Autowired
	private StateRepo sRepo;
	
	
	
	
	@Override
	public UserDto getUser(LoginDto loginDto) {
		// TODO Auto-generated method stub
		String email = loginDto.getEmail();
		String password = loginDto.getPassword();
		
		UserDto uDto=new UserDto();
		
		uDto.setU_email(email);
		uDto.setU_pwd(password);
		
		if(uDto.getU_updatedPwd()!=null)
		{
			return 
		}
		
		
		return null;
	}

	@Override
	public boolean registerUser(RegisterDto rDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean resetPwd(ResetPwdDto pwdDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<Integer, String> getCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, String> getState(Integer cid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, String> getCity(Integer sid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getquote() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUser(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
