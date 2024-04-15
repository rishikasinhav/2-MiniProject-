package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.dto.UserDto;
import com.app.entities.UserDtlsEntity;

public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity, Integer>{

	@Query(value=" select * from USERS_DTLS where email=:email", nativeQuery=true)
	public UserDtlsEntity findByEmail(String email);
	
	@Query(value="select * from USERS_DTLS where email=:email and pwd=:password", nativeQuery=true)
	public UserDtlsEntity findByEmailAndPassword(String email, String password);
	
	//public UserDto getUser(String email);
	
}
