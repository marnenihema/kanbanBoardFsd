package com.cg.utils;

import com.cg.entity.TeamLead;
import com.cg.entity.TeamLeadDto;


public class TeamLeaddtotoTeamLead {
	
	private TeamLeaddtotoTeamLead() {
		
	}

	public static TeamLead parseTeamLeadDtoTeamLead(TeamLeadDto teamleadDto) {
		TeamLead s=new TeamLead();
		s.setFirstName(teamleadDto.getFirstName());
		s.setLastName(teamleadDto.getLastName());
		s.setEmail(teamleadDto.getEmail());
		s.setPassword(teamleadDto.getPassword());
		s.setRole(teamleadDto.getRole());
		s.setYearsOfExpereince(teamleadDto.getYearsOfExpereince());
		s.setTeamLeadId(teamleadDto.getTeamLeadId());
		return s;
		
	}

}
