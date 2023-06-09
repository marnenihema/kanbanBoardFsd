package com.cg.utils;

import com.cg.entity.Project;
import com.cg.entity.ProjectDto;
import com.cg.entity.Status;
import com.cg.entity.StatusDto;
import com.cg.entity.TaskDto;
import com.cg.entity.Tasks;
import com.cg.entity.TeamMember;
import com.cg.entity.TeamMemberDto;

public class TaskdtoToTask {

	private TaskdtoToTask() {
	}

	public static Tasks parseTaskDtotoTasks(TaskDto taskDto) {
		Tasks task = new Tasks();
		task.setTaskId(taskDto.getTaskId());
		task.setTaskname(taskDto.getTaskname());
		task.setDetail(taskDto.getDetail());
		return task;
	}

	public static Status parseStatusDtotoStatus(StatusDto statusDto) {
		Status s = new Status();
		s.setStatusId(statusDto.getStatusId());

		s.setProgress(statusDto.getStatus());
		return s;

	}

	public static TeamMember parseTeamMemberDtotoTeamMember(TeamMemberDto teamMemberDto) {
		TeamMember teamMember = new TeamMember();
		teamMember.setTeamMemberId(teamMemberDto.getTeamMemberId());
		teamMember.setDesignation(teamMemberDto.getDesignation());
		teamMember.setEmail(teamMemberDto.getEmail());
		teamMember.setPassword(teamMemberDto.getPassword());
		teamMember.setRole(teamMemberDto.getRole());
		teamMember.setTmname(teamMemberDto.getTmname());
		teamMember.setYearsOfExpereince(teamMemberDto.getYearsOfExpereince());
		return teamMember;

	}
	
	public static Project parseProjectDtotoProject(ProjectDto project) {
		Project proj =new Project();
		proj.setCreatedate(project.getCreatedate());
		proj.setDescription(project.getDescription());
		proj.setProjectId(project.getProjectId());
		proj.setProjectname(project.getProjectname());
		return proj;
		
		
		
		
		
		
		

}}
