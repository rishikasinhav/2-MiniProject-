package com.app.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import com.app.dto.LoginDto;
import com.app.dto.QuotesDto;
import com.app.dto.RegisterDto;
import com.app.dto.ResetPwdDto;
import com.app.dto.UserDto;
import com.app.entities.CityEntity;
import com.app.entities.CountryEntity;
import com.app.entities.Quotes;
import com.app.entities.StateEntity;
import com.app.entities.UserDtlsEntity;
import com.app.repo.CityRepo;
import com.app.repo.CountryRepo;
import com.app.repo.StateRepo;

import com.app.repo.UserDtlsRepo;
import com.app.utils.EmailUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@Autowired
	private EmailUtils emailUtils;
	
	private QuotesDto[] quotations=null;
	
	//##############################################################################################
	
	@Override
	public UserDto getUser(LoginDto loginDto) {
		// TODO Auto-generated method stub

		
		UserDtlsEntity user= new UserDtlsEntity();
		
		UserDtlsEntity emailAndPassword = uRepo.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
		
		if(emailAndPassword==null)
		{
			return null;
		}
		else
		{
			ModelMapper modelMapper = new ModelMapper();
			UserDto map = modelMapper.map(user, UserDto.class);
			
			return map;
			
		}
		
	}
	
	//#################################################################################################
	@Override
	public boolean registerUser(RegisterDto regDto) {
		// TODO Auto-generated method stub
	
	UserDtlsEntity dtlsEntity = uRepo.findByEmailAndPassword(regDto.getEmail(), regDto.getPwd());
		if(dtlsEntity !=null)
		{
			return false;
		}
		{
			
			ModelMapper mapper=new ModelMapper();
			UserDtlsEntity entity = mapper.map(regDto,UserDtlsEntity.class);
			
			 CountryEntity countryEntity = cRepo.findById(regDto.getCountryId()).orElseThrow();
			 StateEntity stateEntity = sRepo.findById(regDto.getCountryId()).orElseThrow();
			 CityEntity cityEntity = ctyRepo.findById(regDto.getCountryId()).orElseThrow();
			 
			 entity.setCountry(countryEntity);
			 entity.setStateEntity(stateEntity);
			 entity.setCityEntity(cityEntity);
			 
			 entity.setPwd(generateRandom());
			 entity.setUpdatedPwd("no");
			 
			 String subject="User Registration";
			 
			 String body= "your temporary Password : "+generateRandom();
			 emailUtils.sendEmail(regDto.getEmail(), subject, body);
			 
			 UserDtlsEntity save = uRepo.save(entity);
			 
			return save.getUserId()!=null;
		}

	}
	
	//################################################################################################

	@Override
	public boolean resetPwd(ResetPwdDto rDto) {
	
	/*	String email = rDto.getEmail();
		
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
		return false;*/
		
		UserDtlsEntity emailAndPass = uRepo.findByEmailAndPassword(rDto.getEmail(),rDto.getOldPwd());
		
		if(emailAndPass!=null)
		{
			emailAndPass.setPwd(rDto.getNewPwd());
			emailAndPass.setUpdatedPwd("yes");
			uRepo.save(emailAndPass);
			return true;
		}
		else
		{
		return false;
		}
	}
	
	//##########################################################################################

	@Override
	public  Map<Integer,String> getCountry() {
		Map<Integer,String> map=new HashMap<>();
		
		//CountryEntity c=new CountryEntity();
		List<CountryEntity> all = cRepo.findAll();
		
		all.forEach(c->{
			map.put(c.getCountryId(), c.getCountryName());
		});
		
		return map;
	}
	//##########################################################################################

	@Override
	public Map<Integer,String> getState(Integer cid) {
		
		Map< Integer, String> map=new HashMap<>();
		
		CountryEntity country=new CountryEntity();
		country.setCountryId(cid);
		
		StateEntity stateEntity=new StateEntity();
		stateEntity.setCountryEntity(country);
		
		Example<StateEntity> of=Example.of(stateEntity);
		
		List<StateEntity> all = sRepo.findAll(of);
		all.forEach(c->{
		map.put(c.getStateId(),c.getStateName());
		});
		
		return map;
	}
	
	//##########################################################################################
	@Override
	public Map<Integer,String> getCity(Integer sid) {
		
		Map<Integer, String> map=new HashMap<>();
		StateEntity stateEntity=new StateEntity();
		stateEntity.setStateId(sid);
		
		CityEntity cityEntity=new CityEntity();
		cityEntity.setStateEntity(stateEntity);
		
		Example<CityEntity> of=Example.of(cityEntity);
		List<CityEntity> all = ctyRepo.findAll(of);
		all.forEach(c->{
			map.put(c.getCityId(), c.getCityName());
		});
		return map;
	}
	//##########################################################################################
	@Override
	public String getquote() {
		// TODO Auto-generated method stub
		if(quotations==null)
		{
			String url= " https://type.fit/api/quotes";
			
			
			RestTemplate rt=new RestTemplate();
			ResponseEntity<String> forEntity = rt.getForEntity(url,String.class );
			
			String responseBody = forEntity.getBody();
			ObjectMapper mapper=new ObjectMapper();
			
			try {
				 quotations = mapper.readValue(responseBody, QuotesDto[].class);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		Random r=new Random();
		
		int index = r.nextInt(quotations.length-1);	
		//rt.respo
		return quotations[index].getText();
	}
	
	//##########################################################################################
	@Override
	public UserDto getUser(String email) {
		// TODO Auto-generated method stub
		
		UserDtlsEntity user=new UserDtlsEntity();
				
		if(user==null)
		{
			return null;
		}
		else
		{
			ModelMapper mapper=new ModelMapper();
			UserDto map = mapper.map(user, UserDto.class);
			return  map;
		}
	}
	//##############################################################################################
private static String generateRandom() {

	String aToZ="ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
    Random rand=new Random();
    StringBuilder res=new StringBuilder();
    for (int i = 0; i < 5; i++) {
       int randIndex=rand.nextInt(aToZ.length()); 
       res.append(aToZ.charAt(randIndex));            
    }
    
    return res.toString();
    }
}