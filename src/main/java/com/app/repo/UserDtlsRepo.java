package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.dto.UserDto;
import com.app.entities.UserDtlsEntity;

public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity, Integer>{

	public boolean findByEmail(String email);
	
	public boolean findByEmailAndPassword(String email, String password);
	
	public UserDto getUser(String email);
	
}
