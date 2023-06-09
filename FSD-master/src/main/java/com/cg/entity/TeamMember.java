package com.cg.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data


public class TeamMember {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long teamMemberId;
	
	@Size(min = 3, max = 22, message = "Name should be minimum 3 char and max 22 chars")
	@Pattern(regexp = "^[a-zA-Z\\s+]*$", message = "name should have only alphabets")
	private String tmname;
	
	@Email(message = "Not the proper Email ID format! enter again")
	private String email;
	private String password;
	private String designation;
	private int yearsOfExpereince;
	private String role;
	
	
	
}
