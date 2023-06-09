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

import com.cg.entity.Status;
import com.cg.entity.StatusDto;
import com.cg.entity.Tasks;
import com.cg.exceptions.ResourceNotFoundException;
import com.cg.services.StatusService;

@RestController
@RequestMapping("/status")
@CrossOrigin(origins = "http://localhost:4200")
public class StatusController {
	
	@Autowired
	private StatusService statuservice;
	
	
	@PostMapping({"/user/createstatus/{taskId}"})
	public ResponseEntity<Status> createStatus(@RequestBody @Valid StatusDto status,@PathVariable Long taskId) throws ResourceNotFoundException {
		return new ResponseEntity<>(statuservice.createstatus(status,taskId), HttpStatus.CREATED);
	}
	
	@DeleteMapping({"/deleteStatusById/{id}"})
	public ResponseEntity<String> deleteStatus(@PathVariable Long id) throws ResourceNotFoundException {
		statuservice.deletestatus(id);
		return ResponseEntity.ok("Dleted user ");

	}


	@GetMapping("/findstatusById/{id}")
	public ResponseEntity<Status> findStatusById(@PathVariable Long id) throws ResourceNotFoundException {
		return new ResponseEntity<>(statuservice.findStatusById(id), HttpStatus.OK);
	}
	
	@GetMapping("/showAllstatus")
	public ResponseEntity<List<Status>> showStatuss() {
		return ResponseEntity.ok(statuservice.showstatus());

	}
	
	
	@PutMapping({ "/updatestatusById/{uid}"})
	public ResponseEntity<Status> updateStatus(@RequestBody @Valid StatusDto status, @PathVariable Long uid)
			throws ResourceNotFoundException {
		return ResponseEntity.ok(statuservice.updatestatus(status, uid));
	}
	

	@GetMapping("/findStatusByUserId/{id}")
	public ResponseEntity<List<Status>> findstatusByUserId(@PathVariable Long id) throws ResourceNotFoundException {
		return new ResponseEntity<>(statuservice.showTasksByUserId(id), HttpStatus.OK);
	}
//findTasksById
	
	@GetMapping("/my/{id}")
	public ResponseEntity<Tasks> findSById(@PathVariable Long id) throws ResourceNotFoundException {
		return new ResponseEntity<>(statuservice.findTasksById(id), HttpStatus.OK);
	}
	

}
