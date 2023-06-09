package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Project;

@Repository
public interface Projectrepository extends JpaRepository<Project, Long> {

}
