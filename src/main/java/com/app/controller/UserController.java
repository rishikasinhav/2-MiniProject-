package com.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.app.service.UserServiceImpl;

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
			Map<Integer, String> countryMap = userService.getCountry();
			model.addAttribute("countries", countryMap);
		return "register";
		
	}
	
	//##############################################################################
	
	
	//To get state Id based on country
	@GetMapping("/getState/{cid}")
	@ResponseBody
	public Map<Integer, String> getState(@PathVariable("cid") Integer cid)
	{
		
		Map<Integer, String> stateMap = userService.getState(cid);
	
		return stateMap;
	}
	
	//#############################################################################
	
	//To get city Id based on state 
	
	@GetMapping("/getCity/{sid}")
	@ResponseBody
	public Map<Integer, String> getCities(@PathVariable("sid")Integer sid)
	{
		
		Map<Integer, String> cityMap = userService.getCity(sid);
		return cityMap;
	}
	
	//################################################################################
	
	//To handle Register page
	@PostMapping("/registerview")
	public String register(RegisterDto regDto, Model model)
	{
		
			UserDto userDto = userService.getUser(regDto.getEmail());
			if(userDto!=null)
			{
				model.addAttribute("emsg", "Duplicate Email");
				return "registerView";
			}
			else
			{
				boolean registerUser = userService.registerUser(regDto);
				if(registerUser)
				{
					
					model.addAttribute("smsg", "Registration successful");
					return "registerView";
				}
				else
				{
					model.addAttribute("emsg", "Registration failed");
					return "registerView";
				}
				
			}
				
		
	}

	//################################################################################
	
	//To load Login Page
	
	@GetMapping("/")
	public String loginPage(Model model)
	{
		model.addAttribute("loginDto", new LoginDto());
		
		
		return "loginView";
	}
	
	//##############################################################################
	
	//To handle Login page
	@PostMapping("/login")
	public String login(LoginDto loginDto, Model model)
	{
		
		UserDto user2 = userService.getUser(loginDto);
		
		if(user2==null)
		{
			model.addAttribute("emsg", "Invalid Credentials");
			return"redirect:/";
		}
		else
		{
			String updatedPwd = user2.getUpdatedPwd();
			
			if("yes".equals(updatedPwd))
			{
				return "redirect:dashboard";
			}
			else
			{
				return "resetPass";
			}
		}
		
	}
	
	//###############################################################################
	
	//To reset Password
	@PostMapping("/resetPass")
	public String resetPwd(ResetPwdDto pwdDto,Model model)
	{
		UserDto user = userService.getUser(pwdDto.getEmail());
		
		if(pwdDto.getOldPwd()==user.getEmail())
		{
			if(pwdDto.getNewPwd().equals(pwdDto.getConfirmPwd()))
			{
				return "resetPass";
			}
			else
			{
				model.addAttribute("emsg", "Confirm password and new password must be same");
				return"resetPass";
			}
		}
		
		if(user!=null)
		{
			model.addAttribute("email", user.getEmail());
			boolean resetPwd = userService.resetPwd(pwdDto);
			if(resetPwd)
			{
				return "redirect:dashboard";
			}
			else
			{
				model.addAttribute("resetPwd", new ResetPwdDto());
				return"resetPass";
			}
			

		}
				
		
		return "resetPwd";
	}
	
	//###############################################################################
	
	//To load Dashboard Page
	public String Dashboard(Model model)
	{
		String getquote = userService.getquote();
		model.addAttribute("getquote", getquote);
	
		return "dashboard";
	}
	
	//################################################################################
	
	// To logout
	public String Logout()
	{
		return "login";
	}
	
	//###############################################################################
}
