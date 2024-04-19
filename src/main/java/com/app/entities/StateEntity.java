package com.app.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name="STATE_MASTER")
@Data
@Entity
public class StateEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer stateId;
	

	private String stateName;
	
	@ManyToOne
	@JoinColumn(name="country_id")
	private CountryEntity countryEntity;
	
	
	
}
