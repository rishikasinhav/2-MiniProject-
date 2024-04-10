package com.app.entities;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="USERS_DTLS")
@Data
public class UserDtlsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer userId;
	

	private String name;
	

	private String email;
	
	private String password;
	
	
	private Long phone;
	
	
	private Long updatedPwd;
	

	@ManyToOne
	@JoinColumn(name="countryId")
	private CountryEntity countryId;
	
	@ManyToOne
	@JoinColumn(name="stateId")
	private StateEntity stateEntity;

	
	@ManyToOne
	@JoinColumn(name="city_id")
   private CityEntity cityEntity;
	
	@CreationTimestamp
	private LocalDate createdDate;
	
	@UpdateTimestamp
	private LocalDate updatedDate;
	
	////@OneToOne(cascade = CascadeType.ALL,mappedBy = "userEntity")
	//private CountryEntity cEntity;
	
	
	
	//@OneToOne(cascade = CascadeType.ALL,mappedBy = "cityEntity")
	//private CityEntity ctyEntity;
}
