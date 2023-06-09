
package com.cg.services;
 
import java.util.List;
import java.util.Map;

import com.cg.entity.TeamLead;
import com.cg.entity.TeamLeadDto;
import com.cg.exceptions.ResourceNotFoundException;
 

public interface TeamLeadService {
 
    TeamLead createUser(TeamLeadDto user) throws ResourceNotFoundException;
 
    
 
    TeamLead updateUser(TeamLeadDto user, Long uid) throws ResourceNotFoundException;
 
    void deleteUser(Long id) throws ResourceNotFoundException;
 
    TeamLead findUserById(Long id) throws ResourceNotFoundException;
 
    TeamLead authenticateUser(String userName, String password) throws ResourceNotFoundException;
 
    TeamLead changePassword(String oldPassword, String newPassword) throws ResourceNotFoundException;
 
    List<TeamLead> showUsers();



	TeamLead updateUser1(Map<String, Object> fields, Long uid) throws ResourceNotFoundException;
 

	
	String resetForgotPassword(String adminEmail, String newPassword) throws ResourceNotFoundException;






}

