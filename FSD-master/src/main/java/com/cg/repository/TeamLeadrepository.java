package com.cg.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.entity.TeamLead;

@Repository
public interface TeamLeadrepository extends JpaRepository<TeamLead, Long> {
	

	@Query(value = "Select o from TeamLead o where o.firstName=?1 and o.password=?2")
	Optional<TeamLead> loginTeamLead(String userName, String password);

	
	@Query(value = "Select o from TeamLead o where o.email=?1")
	Optional<TeamLead> findByEmail(String email);
	
	@Query(value = "Select o from TeamLead o where o.password=?1")
	Optional<TeamLead> findByPassword(String password);



}
