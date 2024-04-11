package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.CityEntity;


public interface CityRepo extends JpaRepository<CityEntity, Integer>{

	public List<CityEntity> findByStateId(Integer sid);
}
