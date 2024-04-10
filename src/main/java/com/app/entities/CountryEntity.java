package com.app.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="COUNTRY")
@Data
public class CountryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer countryId;
	
	private String countryName;
	

	
}
