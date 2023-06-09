package com.cg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Project;
import com.cg.entity.TaskDto;
import com.cg.entity.Tasks;
import com.cg.entity.TeamMember;
import com.cg.exceptions.ResourceNotFoundException;
import com.cg.repository.Projectrepository;
import com.cg.repository.TasksRepository;
import com.cg.repository.TeamMemberRepository;
import com.cg.utils.TaskdtoToTask;

@Service
public class TasksImpl implements Tasksservice{
	
	@Autowired
	private Projectrepository  prepo;

	
	@Autowired
	private TeamMemberRepository urepo;
	
	@Autowired
	private TasksRepository trepo;

	@Override
	public Tasks createTasks(TaskDto taskDto, Long teamMemberId, Long projectId)
			throws ResourceNotFoundException {
		if(taskDto.getTaskname().equals("")|| taskDto.getDetail().equals("")) {
			throw new ResourceNotFoundException("Task Name and Details should not be empty");
		}
		
		Tasks task=TaskdtoToTask.parseTaskDtotoTasks(taskDto);
		
		TeamMember u=urepo.findById(teamMemberId).orElseThrow(()->new ResourceNotFoundException("TeamMember with  the id "+teamMemberId+" Not found!!!"));
		 
		Project p=prepo.findById(projectId).orElseThrow(()->new ResourceNotFoundException("Project with id "+projectId+" not found!!!"));
		
		
		
		task.setTeamMember(u);
		task.setProject(p);
	
		return trepo.save(task);
	}

	
	@Override
	public Tasks findTasksById(Long id) throws ResourceNotFoundException {
		return trepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task Not found with id : " + id));
	}

	@Override
	public void deleteTasks(Long id) throws ResourceNotFoundException {
	 Optional<Tasks> t=trepo.findById(id);
	    if(!t.isPresent()) {
			throw new ResourceNotFoundException("status  Not found with id : " + id);}
		trepo.deleteById(id);

		
	}

	@Override
	public List<Tasks> showListOfTaskss() throws ResourceNotFoundException {
	
		return trepo.findAll();
	}


	@Override
	public Tasks updateTasks(TaskDto tasks, Long uid,Long teamMemberId) throws ResourceNotFoundException {
		Tasks u = trepo.findById(uid)
				.orElseThrow(() -> new ResourceNotFoundException("TeamMember with id not  found  : " + uid));
		
		TeamMember u1=urepo.findById(teamMemberId)
				.orElseThrow(()->new ResourceNotFoundException("TeamMember with id "+teamMemberId+" not found!!!"));
		Tasks task=TaskdtoToTask.parseTaskDtotoTasks(tasks);
		task.setDetail(tasks.getDetail());
		task.setTaskId(uid);
//		
		task.setTaskname(tasks.getTaskname());
//		
//         
		task.setProject(u.getProject());
		
		task.setTeamMember(u1);
        
		return trepo.save(task);
	}



	@Override
	public List<Tasks> showTasksByTeamMemberId(Long teamMemberId) throws ResourceNotFoundException {
		List<Tasks> list = trepo.findTasksByTeamMemberId(teamMemberId);
		if(list.isEmpty())
			throw new ResourceNotFoundException("TeamMember Not found with id : " + teamMemberId);
		return list;
	
	}


	@Override
	public List<Tasks> showTasksByprojectId(Long projectId) throws ResourceNotFoundException {
		List<Tasks> list = trepo.findTasksByprojectId(projectId);
		if(list.isEmpty())
			throw new ResourceNotFoundException("TeamMember not found with id : " + projectId);
		return list;
	}


	


	@Override
	public Tasks updateTask(TaskDto tasks, Long uid) throws ResourceNotFoundException {
	Optional<Tasks> t=	trepo.findById(uid);
	    if(!t.isPresent()) {
				throw new ResourceNotFoundException("Task not  found with id : " + uid);}
		Tasks task=TaskdtoToTask.parseTaskDtotoTasks(tasks);
		return trepo.save(task);
	}


	
}
