package com.cg.services;

import java.util.List;

import com.cg.entity.TeamMember;
import com.cg.entity.TeamMemberDto;
import com.cg.exceptions.ResourceNotFoundException;


public interface TeamMemberService {

	TeamMember registerTeamMember(TeamMemberDto teamMember) throws ResourceNotFoundException;


	

	TeamMember updateTeamMember(TeamMemberDto teamMember, Long uid) throws ResourceNotFoundException;

	void deleteTeamMember(Long id) throws ResourceNotFoundException;

	TeamMember findTeamMemberById(Long id) throws ResourceNotFoundException;


	TeamMember loginTeamMember(String teamMemberName, String password) throws ResourceNotFoundException;

	TeamMember changePassword(String oldPassword, String newPassword) throws ResourceNotFoundException;

	List<TeamMember> showTeamMembers();


	
	
	
	
	
	
}
