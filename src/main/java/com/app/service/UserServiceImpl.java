package com.app.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.app.dto.LoginDto;
import com.app.dto.RegisterDto;
import com.app.dto.ResetPwdDto;
import com.app.dto.UserDto;
import com.app.entities.CityEntity;
import com.app.entities.CountryEntity;
import com.app.entities.StateEntity;
import com.app.entities.UserDtlsEntity;
import com.app.repo.CityRepo;
import com.app.repo.CountryRepo;
import com.app.repo.StateRepo;
import com.app.repo.UserDtlsRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private  UserDtlsRepo uRepo;
	
	@Autowired
	private CountryRepo cRepo;
	
	@Autowired
	private CityRepo ctyRepo;
	
	@Autowired
	private StateRepo sRepo;
	
	ModelMapper modelMapper = new ModelMapper();
	
	
	//##############################################################################################
	
	@Override
	public UserDto getUser(UserDto userDto) {
		// TODO Auto-generated method stub
	
		//	UserDto u_email = this.getUser(loginDto);
		
			boolean emailAndPassword = uRepo.findByEmailAndPassword(userDto.getU_email(), userDto.getU_pwd());
			
			if(emailAndPassword)
			{
				UserDtlsEntity map = modelMapper.map(userDto, UserDtlsEntity.class);
				
				UserDto user = uRepo.getUser(map.getEmail());
				return user;
			
			}
			else {
				return null;
			}
		
			
		

	}
	
	//#################################################################################################

	@Override
	public boolean registerUser(UserDto userDto) {
		// TODO Auto-generated method stub
		
		boolean byEmail = uRepo.findByEmail(userDto.getU_email());
		
		if(byEmail)
		{
			return false;
		}
		else
		{
			UserDtlsEntity map = modelMapper.map(userDto, UserDtlsEntity.class);
			
			uRepo.save(map);
			
			return true;
			
			
		}

	}
	
	//################################################################################################

	@Override
	public boolean resetPwd(ResetPwdDto rDto) {
	
		String email = rDto.getEmail();
		
		UserDtlsEntity map = modelMapper.map(rDto,UserDtlsEntity.class );
		
			if(map.getUpdatedPwd()!=null)
			{
				return false;
			}
			else
			{
				if(rDto.getNewPwd()==rDto.getConfirmPwd())
				{
					map.setUpdatedPwd(rDto.getConfirmPwd());
				}
			}
		return false;
	}
	
	//##########################################################################################

	@Override
	public  List<CountryEntity> getCountry() {
		
		return cRepo.findAll();
	}
	//##########################################################################################

	@Override
	public List<StateEntity> getState(Integer cid) {
		
		
		return sRepo.findByCountryId(cid);
			
		
		
	}
	
	//##########################################################################################

	@Override
	public List<CityEntity> getCity(Integer sid) {
		
		return ctyRepo.findByStateId(sid);
		
	}
	
	//##########################################################################################

	@Override
	public String getquote() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//##########################################################################################

	/**@Override
	public UserDto getUser(String email) {
		// TODO Auto-generated method stub
		
		
		return null;
	}**/
	
	

}
