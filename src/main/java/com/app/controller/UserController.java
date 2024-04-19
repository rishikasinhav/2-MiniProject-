package com.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.app.entities.UserDtlsEntity;
import com.app.repo.CityRepo;
import com.app.repo.CountryRepo;
import com.app.repo.StateRepo;
import com.app.repo.UserDtlsRepo;
import com.app.service.UserService;
import com.app.service.UserServiceImpl;

@Controller
public class UserController {

	@Autowired
	private CountryRepo cRepo;

	@Autowired
	private StateRepo sRepo;

	@Autowired
	private CityRepo ctyRepo;

	@Autowired
	private UserDtlsRepo uRepo;

	// @Autowired
	// private UserDto uDto;

	@Autowired
	private UserService userService;

	@GetMapping("/check")
	public String load() {
		return "sample";
	}

	// ##########################################################################
	// To Load Register Page
	@GetMapping("/registerView")
	public String registePage(Model model) {

		model.addAttribute("registerDto", new RegisterDto());
		Map<Integer, String> countryMap = userService.getCountry();
		model.addAttribute("countries", countryMap);

		return "registerView";

	}

	// ##############################################################################

	// To get state Id based on country
	@GetMapping("/getState/{cid}")
	@ResponseBody
	public Map<Integer, String> getState(@PathVariable("cid") Integer cid) {

		Map<Integer, String> stateMap = userService.getState(cid);

		return stateMap;
	}

	// #############################################################################

	// To get city Id based on state

	@GetMapping("/getCity/{sid}")
	@ResponseBody
	public Map<Integer, String> getCities(@PathVariable("sid") Integer sid) {

		Map<Integer, String> cityMap = userService.getCity(sid);
		return cityMap;
	}

	// ################################################################################

	// To handle Register page
	@PostMapping("/registerView")
	public String register(RegisterDto regDto, Model model) {
		model.addAttribute("countries", userService.getCountry()); 
		
		/*UserDto userDto = userService.getUser(regDto.getEmail());
		
		
		if (userDto!= null) {
			model.addAttribute("emsg", "Duplicate Email");
			return "registerView";
		}
		
		boolean registerUser = userService.registerUser(regDto);
		if (registerUser) {

			model.addAttribute("smsg", "Registration successful");

		} else {
			model.addAttribute("emsg", "Registration failed");

		}*/
		
	
		UserDto userDto = userService.getUser(regDto.getEmail());
		
	
		if(userDto!=null)
		{
			model.addAttribute("emsg", "duplicate email");
			return "registerView";
		}
		boolean registerUser = userService.registerUser(regDto);
		if(registerUser)
		{
			model.addAttribute("smsg", "registration successful");
		}
		else
		{
			model.addAttribute("emsg", "registration failed");
		}

		return "registerView";

	}

	// ################################################################################

	// To load Login Page

	@GetMapping("/")
	public String loginPage(Model model) {
		model.addAttribute("loginDto", new LoginDto());

		return "login";
	}

	// ##############################################################################

	// To handle Login page
	@PostMapping("/login")
	public String login(LoginDto loginDto, Model model) {

		UserDto user2 = userService.getUser(loginDto);
		

		if (user2 == null) {
	model.addAttribute("emsg", "Invalid credentials");
			
			return "redirect:/";
		}
		else
		{
			String updatedPwd = user2.getUpdatedPwd();

			if ("yes".equals(updatedPwd)) {
				return "redirect:dashboard";
			} else {
				model.addAttribute("resetPwd", new ResetPwdDto());
				
				// model.addAttribute("loginDto", new LoginDto());
				return "resetView";
			}
		}

	}

	// ###############################################################################

	// To reset Password
	@PostMapping("/resetView")
	public String resetPwd(ResetPwdDto pwdDto, Model model) {
		UserDto user = userService.getUser(pwdDto.getEmail());
		//model.addAttribute("resetPwd", new ResetPwdDto());
		if (pwdDto.getOldPwd() == user.getPwd()) {
			if (!pwdDto.getNewPwd().equals(pwdDto.getConfirmPwd())) {
				model.addAttribute("emsg", "Confirm password and new password must be same");
				return "resetView";
			}

		}

		if (user != null) {
			model.addAttribute("email", user.getEmail());
			boolean resetPwd = userService.resetPwd(pwdDto);
			if (resetPwd) {
				return "redirect:dashboard";
			} else {
				model.addAttribute("resetPwd", new ResetPwdDto());
				return "resetView";
			}

		}
		return "resetView";
	}

	// ###############################################################################

	// To load Dashboard Page
	@PostMapping("/dashboard")
	public String Dashboard(Model model) {
		String getquote = userService.getquote();
		model.addAttribute("getquote", getquote);

		return "dashboard";
	}

	// ################################################################################

	// To logout
	@GetMapping("/logout")
	public String Logout() {
		return "login";
	}

	// ###############################################################################
}
