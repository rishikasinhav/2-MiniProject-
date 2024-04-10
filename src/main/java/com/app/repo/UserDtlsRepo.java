package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.UserDtlsEntity;

public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity, Integer>{

	
}
