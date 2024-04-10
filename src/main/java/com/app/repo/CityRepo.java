package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.CityEntity;

public interface CityRepo extends JpaRepository<CityEntity, Integer>{

}
