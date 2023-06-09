package com.cg.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeamMemberDto {
	private Long teamMemberId;
	private String tmname;
	private String email;
	private String password;
	private String designation;
	private int yearsOfExpereince;
	private String role;

}
