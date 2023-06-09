package com.cg.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminVerification {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String otp;
	
	private String email;
	
  
	
	

}
