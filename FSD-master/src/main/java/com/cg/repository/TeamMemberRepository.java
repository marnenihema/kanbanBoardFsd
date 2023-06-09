package com.cg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.entity.TeamMember;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
	

	@Query(value = "Select o from TeamMember o where o.tmname=?1 and o.password=?2")
	Optional<TeamMember> loginTeamMember(String userName, String password);
	
	@Query(value = "Select o from TeamMember o where o.email=?1")
	Optional<TeamMember> findByEmail(String email);
	
	@Query(value = "Select o from TeamMember o where o.password=?1")
	Optional<TeamMember> findByPassword(String password);


}
