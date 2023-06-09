package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Status;

@Repository
public interface Statusrepository extends JpaRepository<Status, Long> {

	
	List<Status> findByTaskssTeamMemberTeamMemberId(Long userId);
}
