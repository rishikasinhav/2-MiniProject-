package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.CityEntity;


public interface CityRepo extends JpaRepository<CityEntity, Integer>{

	@Query(value="Select * from CITY_MASTER where stateId=:sid", nativeQuery = true)
	public List<CityEntity> findByStateId(Integer sid);
}
