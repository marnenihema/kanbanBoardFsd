package com.cg.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

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
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long projectId;
	
	private String description;
	
	private String projectname;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate createdate;

}
