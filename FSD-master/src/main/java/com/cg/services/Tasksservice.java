package com.cg.services;

import java.util.List;

import com.cg.entity.TaskDto;
import com.cg.entity.Tasks;
import com.cg.exceptions.ResourceNotFoundException;

public interface Tasksservice {
	
	Tasks createTasks(TaskDto taskDto,Long teamMemberId,Long projectId) throws ResourceNotFoundException;

	Tasks updateTasks(TaskDto tasks,Long id,Long teamMemberId) throws ResourceNotFoundException;

	Tasks findTasksById(Long id) throws ResourceNotFoundException;

	void deleteTasks(Long id) throws ResourceNotFoundException;

	List<Tasks> showListOfTaskss() throws ResourceNotFoundException;
	
	List<Tasks> showTasksByTeamMemberId(Long teamMemberId)  throws ResourceNotFoundException;
	
	List<Tasks> showTasksByprojectId(Long projectId)  throws ResourceNotFoundException;


	

	 Tasks updateTask(TaskDto tasks, Long uid) throws ResourceNotFoundException ;
}
