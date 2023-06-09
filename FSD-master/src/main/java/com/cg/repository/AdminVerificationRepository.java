package com.cg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.entity.AdminVerification;

public interface AdminVerificationRepository extends JpaRepository<AdminVerification,Integer> {

	@Query(value = "Select o from AdminVerification o where o.email=?1")
	Optional<AdminVerification> findByEmail(String email);
	
	Optional<AdminVerification> findFirstByEmail(String email);
	
}
