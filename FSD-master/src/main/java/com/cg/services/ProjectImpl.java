package com.cg.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Project;
import com.cg.entity.ProjectDto;
import com.cg.exceptions.ResourceNotFoundException;
import com.cg.repository.Projectrepository;
import com.cg.utils.TaskdtoToTask;

@Service
public class ProjectImpl implements ProjectService {

	@Autowired
	private Projectrepository repository;

	@Override
	public Project createProject(ProjectDto project) throws ResourceNotFoundException {
		if (project.getProjectname().equals("") || project.getDescription().equals(""))
			throw new ResourceNotFoundException("Project Name and description should not be empty");
		Project s = TaskdtoToTask.parseProjectDtotoProject(project);

		s.setCreatedate(LocalDate.now());
		return repository.save(s);
	}

	@Override
	public Project updateProject(ProjectDto project, Long uid) throws ResourceNotFoundException {

		Project u = repository.findById(uid)
				.orElseThrow(() -> new ResourceNotFoundException("project not  found with id : " + uid));
		u.setDescription(project.getDescription());
		u.setProjectId(uid);
		u.setCreatedate(LocalDate.now());
		u.setProjectname(project.getProjectname());

		return repository.save(u);
	}

	@Override
	public void deleteproject(Long id) throws ResourceNotFoundException {
	Optional<Project> p=repository.findById(id);
	if(!p.isPresent())
	   throw new ResourceNotFoundException("Project Not found with id"+id);
		repository.deleteById(id);

	}

	@Override
	public Project findProjectById(Long id) throws ResourceNotFoundException {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Project Not found with id : " + id));
	}

	@Override
	public List<Project> showProjects() {

		return repository.findAll();
	}

}
