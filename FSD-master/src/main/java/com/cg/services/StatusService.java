package com.cg.services;

import java.util.List;

import com.cg.entity.Status;
import com.cg.entity.StatusDto;
import com.cg.entity.Tasks;
import com.cg.exceptions.ResourceNotFoundException;


public interface StatusService {
	Status createstatus(StatusDto statusdto,Long taskId) throws ResourceNotFoundException;
	
	void deletestatus(long id) throws ResourceNotFoundException;
	
	List<Status> showstatus();
	
	Status  updatestatus(StatusDto statusdto , Long uid) throws ResourceNotFoundException;

	Status findStatusById(Long id) throws ResourceNotFoundException;
	Tasks findTasksById(Long id) throws ResourceNotFoundException;
	
	List<Status> showTasksByUserId(Long userId)  throws ResourceNotFoundException;
	
	
}
