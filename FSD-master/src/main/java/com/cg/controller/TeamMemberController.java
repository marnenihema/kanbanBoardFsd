package com.cg.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.TeamMember;
import com.cg.entity.TeamMemberDto;
import com.cg.exceptions.ResourceNotFoundException;
import com.cg.services.TeamMemberService;

@RestController
@RequestMapping("/teamMembers")
@CrossOrigin(origins = "http://localhost:4200")
public class TeamMemberController {
	
	@Autowired
	private TeamMemberService teamMemberService;

	@PostMapping({"/teamMember/register-tm"})
	public ResponseEntity<TeamMember> registerTeamMember(@RequestBody @Valid TeamMemberDto teamMember) throws ResourceNotFoundException {
		return new ResponseEntity<>(teamMemberService.registerTeamMember(teamMember), HttpStatus.CREATED);
	}

	


	@PutMapping({ "/updateTeamMemberById/{uid}"})
	public ResponseEntity<TeamMember> updateTeamMember(@RequestBody @Valid TeamMemberDto teamMember, @PathVariable Long uid)
			throws ResourceNotFoundException {
		return ResponseEntity.ok(teamMemberService.updateTeamMember(teamMember, uid));
	}

	@DeleteMapping({"/deleteTeamMemberById/{id}"})
	public ResponseEntity<String> deleteTeamMember(@PathVariable Long id) throws ResourceNotFoundException {
		teamMemberService.deleteTeamMember(id);
		return ResponseEntity.ok("Dleted teamMember ");

	}

	@GetMapping("/findTeamMemberById/{id}")
	public ResponseEntity<TeamMember> findTeamMemberById(@PathVariable Long id) throws ResourceNotFoundException {
		return new ResponseEntity<>(teamMemberService.findTeamMemberById(id), HttpStatus.OK);
	}

	
	@GetMapping("/loginTeamMember/{teamMemberName}/{password}")
	public ResponseEntity<TeamMember> loginTeamMember(@PathVariable String teamMemberName, @PathVariable String password)
			throws ResourceNotFoundException {
		return ResponseEntity.ok(teamMemberService.loginTeamMember(teamMemberName, password));
	}

	@PutMapping({"changePassword/{oldPassword}/{newPassword}/{teamMemberId}"})
	public ResponseEntity<TeamMember> changePassword(@PathVariable String oldPassword, @PathVariable String newPassword) throws ResourceNotFoundException {
		return ResponseEntity.ok(teamMemberService.changePassword(oldPassword, newPassword));

	}

	@GetMapping("/showAllTeamMembers")
	public ResponseEntity<List<TeamMember>> showTeamMembers() {
		return ResponseEntity.ok(teamMemberService.showTeamMembers());

	}


}
