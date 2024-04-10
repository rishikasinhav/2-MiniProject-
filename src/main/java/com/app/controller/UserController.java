package com.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.dto.LoginDto;
import com.app.dto.RegisterDto;
import com.app.dto.ResetPwdDto;
import com.app.dto.UserDto;
import com.app.entities.CountryEntity;
import com.app.repo.CityRepo;
import com.app.repo.CountryRepo;
import com.app.repo.StateRepo;
import com.app.repo.UserDtlsRepo;
import com.app.service.UserService;

public class UserController {
	
	@Autowired
	private CountryRepo cRepo;
	
	@Autowired
	private StateRepo sRepo;

	@Autowired
	private CityRepo ctyRepo;
	
	@Autowired
	private UserDtlsRepo uRepo;
	
	@Autowired
	private UserDto uDto;
	
	@Autowired
	private UserService userService;
	
	//##########################################################################
	//To Load Register Page
	@GetMapping("/register")
	public String registePage(Model model)
	{
		model.addAttribute("register", new RegisterDto());
		return "register";
		
	}
	
	//##############################################################################
	
	
	//To get state Id based on country
	@GetMapping("/getState/{cid}")
	public Map<Integer, String> getState(@PathVariable("cid") Integer cid)
	{
	
		Map<Integer, String >  map=new HashMap();
		String string = map.get(cid);
		
		
		return map;
	}
	
	//#############################################################################
	
	//To get city Id based on state 
	
	@GetMapping("/getCity/{sid}")
	public Map<Integer, String> getCities(@PathVariable("sid")Integer sid)
	{
		return null;
	}
	
	//################################################################################
	
	//To handle Register page
	public String register(RegisterDto regDto, Model model)
	{
		
		return null;
	}

	//################################################################################
	
	//To load Login Page
	
	public String loginPage(Model model)
	{
		return null;
	}
	
	//##############################################################################
	
	//To handle Login page
	public String login(LoginDto loginDto, Model model)
	{
		return null;
	}
	
	//###############################################################################
	
	//To reset Password
	
	public String resetPwd(ResetPwdDto pwdDto)
	{
		return "resetPwd";
	}
	
	//###############################################################################
	
	//To load Dashboard Page
	public String Dashboard(Model model)
	{
		return null;
	}
	
	//################################################################################
	
	// To logout
	public String Logout()
	{
		return "login";
	}
	
	//###############################################################################
}
