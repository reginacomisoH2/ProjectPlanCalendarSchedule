package com.h2software.calendarschedules.system.java.beans;

import java.util.ArrayList;
import java.util.Date;

public class ProjectTask implements Comparable<ProjectTask>{

	private String projectTaskName;
	private int projectTaskId;
	private int taskDuration;
	private Date taskStartDate;
	private Date taskEndDate;
	private ArrayList<Integer> taskDependency;
	private ArrayList<Integer> taskDependencyHolder;
	
	public ArrayList<Integer> getTaskDependency() {
		return taskDependency;
	}
	public void setTaskDependencyHolder(ArrayList<Integer> taskDependencyHolder) {
		this.taskDependencyHolder = taskDependencyHolder;
	}
	public ArrayList<Integer> getTaskDependencyHolder() {
		return taskDependencyHolder;
	}
	public void setTaskDependency(ArrayList<Integer> taskDependency) {
		this.taskDependency = taskDependency;
	}
	public String getProjectTaskName() {
		return projectTaskName;
	}
	public void setProjectTaskName(String projectTaskName) {
		this.projectTaskName = projectTaskName;
	}
	public int getProjectTaskId() {
		return projectTaskId;
	}
	public void setProjectTaskId(int projectTaskId) {
		this.projectTaskId = projectTaskId;
	}
	public int getTaskDuration() {
		return taskDuration;
	}
	public void setTaskDuration(int taskDuration) {
		this.taskDuration = taskDuration;
	}
	public Date getTaskStartDate() {
		return taskStartDate;
	}
	public void setTaskStartDate(Date taskStartDate) {
		this.taskStartDate = taskStartDate;
	}
	public Date getTaskEndDate() {
		return taskEndDate;
	}
	public void setTaskEndDate(Date taskEndDate) {
		this.taskEndDate = taskEndDate;
	}
	@Override
	public int compareTo(ProjectTask projectTask) {
		//start date comparison
		int compare = 2;
		if(this.getTaskStartDate() != null) {
		compare = this.getTaskStartDate().compareTo(projectTask.getTaskStartDate()) < 0 ? -1 : 
			(this.getTaskStartDate().compareTo(projectTask.getTaskStartDate()) == 0 ? 0 : 1);
		
		if (compare == 0) {
			//end date comparison
			compare = this.getTaskEndDate().compareTo(projectTask.getTaskEndDate()) < 0 ? -1 : 
				(this.getTaskEndDate().compareTo(projectTask.getTaskEndDate()) == 0 ? 0 : 1);
		}
		
		if (compare == 0) {
			//taskId comparison
			compare = this.getProjectTaskId() < (projectTask.getProjectTaskId()) ? -1 : 
				(this.getProjectTaskId() == (projectTask.getProjectTaskId()) ? 0 : 1);
		}
		}
		/*
		//sort based on taskDependency
		int compare = this.taskDependency.size() < projectTask.taskDependency.size() ? -1 : 
			(this.taskDependency.size() == projectTask.taskDependency.size() ? 0 : 1);
		
		//if taskDependency is zero, then sort based on Duration
		if (this.taskDependency.size() == projectTask.taskDependency.size() && this.taskDependency.size() == 0) {
			compare = this.taskDuration < projectTask.taskDuration ? -1 : (this.taskDuration == projectTask.taskDuration ? 0 : 1);
		}*/
		return compare;
	}
}
