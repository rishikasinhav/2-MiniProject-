package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.StateEntity;

public interface StateRepo extends JpaRepository<StateEntity, Integer>{

	public List<StateEntity> findByCountryId(Integer cid);
}
