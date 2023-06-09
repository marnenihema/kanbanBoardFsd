package com.cg.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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



public class TeamLead {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long teamLeadId;
	
	
	//@NotEmpty(message = "Name shouldn't be Empty or null ....!!")
	@Size(min = 3, max = 22, message = "Name should be minimum 5 char and max 22 chars")
	@Pattern(regexp = "^[a-zA-Z\\s+]*$", message = "name should have only alphabets")
	private String firstName;
	
	
	
	@Size(min = 3, max = 22, message = "Name should be minimum 5 char and max 22 chars")
	@Pattern(regexp = "^[a-zA-Z\\s+]*$", message = "name should have only alphabets")
	private String lastName;
	
	
	
	@Email(message = "Not the proper Email ID format! enter again")
	private String email;
	
	
	private String password;
	
	@Min(value=1,message="Minimum experience should be 1")
	@Max(value=10,message="Maximum experience should be 10")
	private int yearsOfExpereince;
	
	
	private String role;
	

}
