package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.StateEntity;

public interface StateRepo extends JpaRepository<StateEntity, Integer>{

	@Query(value="select * from STATE_MASTER where countryId=:cid", nativeQuery = true)
	public List<StateEntity> findByCountryId(Integer cid);
}
