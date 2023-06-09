package com.cg.services;

import java.util.List;

import com.cg.entity.Project;
import com.cg.entity.ProjectDto;
import com.cg.exceptions.ResourceNotFoundException;

public interface ProjectService {
	
	Project createProject(ProjectDto project) throws ResourceNotFoundException;

	
	

	Project updateProject(ProjectDto project , Long uid) throws ResourceNotFoundException;

	void deleteproject(Long id) throws ResourceNotFoundException;

	Project findProjectById(Long id) throws ResourceNotFoundException;

	List<Project> showProjects();
	

}
