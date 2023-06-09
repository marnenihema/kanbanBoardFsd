package com.cg.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"tasks_details_fk"})})

public class Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long statusId;

	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private Progress progress;
	

	
	

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "tasks_details_fk",referencedColumnName = "taskId" ,unique = true)	
	private Tasks taskss;


	
}
