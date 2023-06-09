package com.cg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class TeamLeadDto {
	

	private Long teamLeadId;
	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private int yearsOfExpereince;

	private String role;

}
