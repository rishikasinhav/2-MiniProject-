package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.CountryEntity;

public interface CountryRepo  extends JpaRepository<CountryEntity, Integer>{

}
