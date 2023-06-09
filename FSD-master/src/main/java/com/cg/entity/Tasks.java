package com.cg.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tasks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long taskId;
	
	private String taskname;
	
	
	private String detail;
	
	
	
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "project_details_fk",referencedColumnName = "projectId")	
	private Project project;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_details_fk",referencedColumnName = "teamMemberId")	
	private TeamMember teamMember;
	
	


	
	
	
	
	

}
