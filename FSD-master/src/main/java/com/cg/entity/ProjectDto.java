package com.cg.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectDto {

	private Long projectId;
	
	private String description;
	
	private String projectname;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate createdate;

}
