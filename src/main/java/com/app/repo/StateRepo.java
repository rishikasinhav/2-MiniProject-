package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.StateEntity;

public interface StateRepo extends JpaRepository<StateEntity, Integer>{

}
