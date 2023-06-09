package com.cg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Status;
import com.cg.entity.StatusDto;
import com.cg.entity.Tasks;
import com.cg.exceptions.ResourceNotFoundException;
import com.cg.repository.Statusrepository;
import com.cg.repository.TasksRepository;
import com.cg.utils.TaskdtoToTask;

@Service
public class StatusImpl implements StatusService{
	
	@Autowired
	private Statusrepository repository;
	
	@Autowired
	private  TasksRepository trepo;


	@Override
	public Status createstatus(StatusDto statusdto,Long taskId) throws ResourceNotFoundException{ 
		//parseStatusDtotoStatus
		
		Status s=TaskdtoToTask.parseStatusDtotoStatus(statusdto);
		Tasks p=trepo.findById(taskId).orElseThrow(()->new ResourceNotFoundException("Tasks with id "+taskId+" not found!!!"));
	    s.setTaskss(p);
		return repository.save(s);
	}

	@Override
	public void deletestatus(long id) throws ResourceNotFoundException {
		 Optional<Status> s=repository.findById(id);
		 if(!s.isPresent())
				throw  new ResourceNotFoundException("status Not found with id : " + id);
		repository.deleteById(id);		
	}

	@Override
	public List<Status> showstatus() {
		return repository.findAll();
	}

	@Override
	public Status updatestatus(StatusDto statusdto, Long uid) throws ResourceNotFoundException {
		
		Status  u = repository.findById(uid)
				.orElseThrow(() -> new ResourceNotFoundException("Status  Not found with id : " + uid));
        
         u.setProgress(statusdto.getStatus());
         u.setTaskss(u.getTaskss());
		return repository.save(u);	
	
	}


	@Override
	public Status findStatusById(Long id) throws ResourceNotFoundException {
		return  repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("status  Not found with id : " + id));
		

	}

	@Override
	public List<Status> showTasksByUserId(Long userId) throws ResourceNotFoundException {
	
		return repository.findByTaskssTeamMemberTeamMemberId(userId);
	}

	@Override
	public Tasks findTasksById(Long id) throws ResourceNotFoundException {
		
		return trepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Tasks with id "+id+" not found!!!"));
		
	}	

}
