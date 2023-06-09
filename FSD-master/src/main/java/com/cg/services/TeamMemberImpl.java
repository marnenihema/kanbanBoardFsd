package com.cg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.TeamMember;
import com.cg.entity.TeamMemberDto;
import com.cg.exceptions.EmailAlreadyExistsException;
import com.cg.exceptions.ResourceNotFoundException;
import com.cg.repository.TeamMemberRepository;
import com.cg.utils.TaskdtoToTask;

@Service
public class TeamMemberImpl implements TeamMemberService {
	@Autowired
	private TeamMemberRepository repository;

	@Override
	public TeamMember registerTeamMember(TeamMemberDto teamMember) throws ResourceNotFoundException {
		
		
		TeamMember teammember=TaskdtoToTask.parseTeamMemberDtotoTeamMember(teamMember);
		Optional<TeamMember> optionalTeamMember = repository.findByEmail(teamMember.getEmail());
		 
        if(optionalTeamMember.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists for TeamMember");
        }

        Optional<TeamMember> optionalTeamMember1 = repository.findByPassword(teamMember.getPassword());
        if(optionalTeamMember1.isPresent()){
            throw new EmailAlreadyExistsException("PASSWORD  ALREADY EXISTS TRY WITH ANOTHER PASSWORD");
        }
        teammember.setRole("TEAMMEMBER");
        return repository.save(teammember);


	
	}
	@Override
	public TeamMember updateTeamMember(TeamMemberDto teamMember, Long uid) throws ResourceNotFoundException {
		Optional<TeamMember> t= repository.findById(uid);
		if(!t.isPresent()) {
				throw  new ResourceNotFoundException("TeamMember not found with id : " + uid);}
		 TeamMember teammember=TaskdtoToTask.parseTeamMemberDtotoTeamMember(teamMember);   
		 
		 teammember.setRole("TEAMMEMBER");
		 teammember.setTeamMemberId(uid);
		return repository.save(teammember);
	}

	@Override
	public void deleteTeamMember(Long id) throws ResourceNotFoundException {
		Optional<TeamMember> u=repository.findById(id);
				if(!u.isPresent()) {
				throw new ResourceNotFoundException("TeamMember Not Found with id : " + id);}
		repository.deleteById(id);
		
		
		
		
		
		

	}

	@Override
	public TeamMember findTeamMemberById(Long id) throws ResourceNotFoundException {

		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("TeamMember Not found with id : " + id));
	}

	@Override
	public TeamMember loginTeamMember(String teamMemberName, String password) throws ResourceNotFoundException {

		return repository.loginTeamMember(teamMemberName, password)
				.orElseThrow(() -> new ResourceNotFoundException("Bad or Invalid Credentials"));
	}

	@Override
	public TeamMember changePassword(String oldPassword, String newPassword) throws ResourceNotFoundException {
		TeamMember u = repository.findByPassword(oldPassword)
				.orElseThrow(() -> new ResourceNotFoundException("teamMember password not exists"));
		if (u.getPassword().equals(oldPassword)) {
			u.setPassword(newPassword);
			repository.save(u);
		} else {
			throw new ResourceNotFoundException("Old password is not currect");
		}
		return u;
	}

	@Override
	public List<TeamMember> showTeamMembers() {
		return repository.findAll();
	}

}
