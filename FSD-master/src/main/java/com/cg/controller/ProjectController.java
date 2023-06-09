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

import com.cg.entity.Project;
import com.cg.entity.ProjectDto;
import com.cg.exceptions.ResourceNotFoundException;
import com.cg.services.ProjectService;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {
	
	@Autowired
	private ProjectService projectservice;
	
	@PostMapping({"/createProject"})
	public ResponseEntity<Project> createProject(@RequestBody @Valid ProjectDto project1) throws ResourceNotFoundException {
		return new ResponseEntity<>(projectservice.createProject(project1), HttpStatus.CREATED);
	}

	@PutMapping({ "/updateProjectById/{uid}"})
	public ResponseEntity<Project> updateProject(@RequestBody @Valid ProjectDto project, @PathVariable Long uid)
			throws ResourceNotFoundException {
		return ResponseEntity.ok(projectservice.updateProject(project, uid));
	}

	@DeleteMapping({"/deleteProjectById/{id}"})
	public ResponseEntity<String> deleteProject(@PathVariable Long id) throws ResourceNotFoundException {
		projectservice.deleteproject(id);
		return ResponseEntity.ok("Dleted user ");

	}

	@GetMapping("/findProjectById/{id}")
	public ResponseEntity<Project> findProjectById(@PathVariable Long id) throws ResourceNotFoundException {
		return new ResponseEntity<>(projectservice.findProjectById(id), HttpStatus.OK);
	}

	@GetMapping("/showAllProjects")
	public ResponseEntity<List<Project>> showProjects() {
		return ResponseEntity.ok(projectservice.showProjects());

	}


	
	
	
	
	
	
	
	
	

}
