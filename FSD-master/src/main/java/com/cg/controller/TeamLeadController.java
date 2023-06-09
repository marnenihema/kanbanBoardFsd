
package com.cg.controller;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.ForgotPassDTO;
import com.cg.entity.TeamLead;
import com.cg.entity.TeamLeadDto;
import com.cg.exceptions.ResourceNotFoundException;
import com.cg.services.AdminAuthenticationService;
import com.cg.services.TeamLeadService;

@RestController
@RequestMapping("/teamlead")
@CrossOrigin(origins = "http://localhost:4200")
public class TeamLeadController {

	@Autowired
	public TeamLeadService teamleadService;
	
	@Autowired
	private AdminAuthenticationService adminAuthenticationService;


	@PostMapping({ "/RegisterTeamLead" })
	public ResponseEntity<TeamLead> createTeamLead(@RequestBody @Valid TeamLeadDto user) throws ResourceNotFoundException {
		return new ResponseEntity<>(teamleadService.createUser(user), HttpStatus.CREATED);

	}

	@PutMapping({ "/updateTeamLeadById/{uid}" })
	public ResponseEntity<TeamLead> updateTeamLead(@RequestBody  TeamLeadDto user, @PathVariable Long uid)
			throws ResourceNotFoundException {
		return ResponseEntity.ok(teamleadService.updateUser(user, uid));

	}

	@DeleteMapping({ "/deleteTeamLeadById/{id}" })
	public ResponseEntity<String> deleteTeamLead(@PathVariable Long id) throws ResourceNotFoundException {
		teamleadService.deleteUser(id);
		return ResponseEntity.ok("Dleted user ");

	}

	@GetMapping("/findTeamLeadById/{id}")
	public ResponseEntity<TeamLead> findTeamLeadById(@PathVariable Long id) throws ResourceNotFoundException {
		return new ResponseEntity<>(teamleadService.findUserById(id), HttpStatus.OK);

	}

	@GetMapping("/authenticateTeamLead/{name}/{password}")
	public ResponseEntity<TeamLead> authenticateTeamLead(@PathVariable String name, @PathVariable String password)
			throws ResourceNotFoundException {
		return ResponseEntity.ok(teamleadService.authenticateUser(name, password));

	}

	@PutMapping({ "changePassword/{oldPassword}/{newPassword}" })
	public ResponseEntity<String> changePassword(@PathVariable String oldPassword, @PathVariable String newPassword
			) throws ResourceNotFoundException {
		teamleadService.changePassword(oldPassword, newPassword);
		return ResponseEntity.ok("password changed Successfully");

	}

	@GetMapping("/showAllTeamLeads")
	public ResponseEntity<List<TeamLead>> showTeamLead() {
		return ResponseEntity.ok(teamleadService.showUsers());

	}
	
    @PatchMapping("/{id}")
    public ResponseEntity<TeamLead> updateteamLead1(@RequestBody Map<String, Object> fields,@PathVariable Long id) throws ResourceNotFoundException{
        return ResponseEntity.ok(teamleadService.updateUser1(fields,id));
    }

	@PutMapping("/forgotPassword")
	public ResponseEntity<String> forgotPassword(@RequestBody ForgotPassDTO r) {
		return ResponseEntity.ok("Password reset successfully");
		
    }
	@PostMapping("/sendEmail/{email}")
	public ResponseEntity<String> sendEmail(@PathVariable String email) {
		
		return ResponseEntity.ok("Mail sent successfully");
	}

}
