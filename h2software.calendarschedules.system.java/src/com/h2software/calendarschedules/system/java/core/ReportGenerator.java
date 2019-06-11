package com.h2software.calendarschedules.system.java.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.h2software.calendarschedules.system.java.beans.ProjectPlan;
import com.h2software.calendarschedules.system.java.beans.ProjectTask;

public class ReportGenerator {
	ArrayList<ProjectTask> projectTaskArray;
	ProjectPlan projectPlan;
	String projectPlanBody;
	String projectTaskBody;
	String firstLastLine;
	public ReportGenerator(ProjectPlan projectPlan, ArrayList<ProjectTask> projectTaskArray){
		this.projectPlan = projectPlan;
		this.projectTaskArray = projectTaskArray;
		this.projectPlanBody = "";
		this.projectTaskBody = "";
		this.firstLastLine = "";
		
	}

	public void generateReport(){

		layoutProjectTasks();
		layoutProjectPlan();
		
		String report = firstLastLine + System.lineSeparator() + projectPlanBody + System.lineSeparator() + projectTaskBody + firstLastLine;
		System.out.println(report);
	}

	private void layoutProjectTasks(){

		String taskIdHeader = "Task-ID";
		String startDateHeader = "Start-Date";
		String endDateHeader = "End-Date";
		String durationHeader = "Duration(Days)";
		String taskHeader = "Task";
		String dependenciesHeader = "Dependencies (Task-ID)";

		//variables to calculate task header lengths
		//constant
		int startDateLen = startDateHeader.length();
		int endDateLen = endDateHeader.length();
		int durationLen = durationHeader.length();

		//varying length ~ depends on usesr's input
		int taskIdMaxLen = taskIdHeader.length();
		int taskNameLen = taskHeader.length();
		int dependenciesLen = dependenciesHeader.length();

		int columnSpace = 5;

		if (projectTaskArray.size() > 0){	
			//column length calculation
			for (ProjectTask x: projectTaskArray ){
				//calculate max size of task column
				if (taskIdMaxLen < Integer.toString(x.getProjectTaskId()).length())
					taskIdMaxLen = Integer.toString(x.getProjectTaskId()).length();

				//calculate max size of duration column
				if (durationLen < Integer.toString(x.getTaskDuration()).length())
					durationLen = Integer.toString(x.getTaskDuration()).length();

				if(taskNameLen < x.getProjectTaskName().length())
					taskNameLen = x.getProjectTaskName().length();

				String taskDependencyString = "";
				if (x.getTaskDependency().size() > 0){
					for (int taskDependencyInt : x.getTaskDependency()){
						taskDependencyString += taskDependencyInt + ", ";
					}
					taskDependencyString = taskDependencyString.substring(0, (taskDependencyString.length()-2));
				}

				if (dependenciesLen < taskDependencyString.length())
					dependenciesLen = taskDependencyString.length();
			}

		}


		String projectTaskString1 = "# Project Tasks" + System.lineSeparator();
		StringBuilder projectTaskString = new StringBuilder(); 
		//Headers
		//================================================================
		projectTaskString.append(taskIdHeader);

		if((taskIdMaxLen - taskIdHeader.length()) > 0){
			int diff = taskIdMaxLen - taskIdHeader.length();
			for(int x = 0; x < diff; x++){
				projectTaskString.append(' ');
			}
		}

		for(int x = 0; x < columnSpace; x++){
			projectTaskString.append(' ');	 		
		}

		projectTaskString.append(startDateHeader);
		for(int x = 0; x < columnSpace; x++){
			projectTaskString.append(' ');	 		
		}


		projectTaskString.append(endDateHeader);
		for(int x = 0; x < columnSpace; x++){
			projectTaskString.append(' ');	 		
		}


		projectTaskString.append(durationHeader);
		for(int x = 0; x < columnSpace; x++){
			projectTaskString.append(' ');	 		
		}

		projectTaskString.append(taskHeader);

		if((taskNameLen - taskHeader.length()) > 0){
			int diff = taskNameLen - taskHeader.length();
			for(int x = 0; x < diff; x++){
				projectTaskString.append(' ');
			}
		}

		for(int x = 0; x < columnSpace; x++){
			projectTaskString.append(' ');	 		
		}

		projectTaskString.append(dependenciesHeader);

		if((dependenciesLen - dependenciesHeader.length()) > 0){
			int diff = dependenciesLen - dependenciesHeader.length();
			for(int x = 0; x < diff; x++){
				projectTaskString.append(' ');
			}
		}

		//underline / dashes
		//================================================================
		projectTaskString.append(System.lineSeparator());
		for(int x = 0; x < taskIdMaxLen; x++) {
			projectTaskString.append('-');
		}
		for(int x = 0; x < columnSpace; x++){
			projectTaskString.append(' ');	 		
		}

		for(int x = 0; x < startDateLen; x++) {
			projectTaskString.append('-');
		}
		for(int x = 0; x < columnSpace; x++){
			projectTaskString.append(' ');	 		
		}

		for(int x = 0; x < endDateLen; x++) {
			projectTaskString.append('-');
		}
		for(int x = 0; x < columnSpace; x++){
			projectTaskString.append(' ');	 		
		}

		for(int x = 0; x < durationLen; x++) {
			projectTaskString.append('-');
		}
		for(int x = 0; x < columnSpace; x++){
			projectTaskString.append(' ');	 		
		}

		for(int x = 0; x < taskNameLen; x++) {
			projectTaskString.append('-');
		}
		for(int x = 0; x < columnSpace; x++){
			projectTaskString.append(' ');	 		
		}

		for(int x = 0; x < dependenciesLen; x++){
			projectTaskString.append('-');	 		
		}
		for(int x = 0; x < columnSpace; x++){
			projectTaskString.append(' ');	 		
		}
		projectTaskString.append(System.lineSeparator());
		//================================================================
		//each task

		StringBuilder projectTaskString2 = new StringBuilder(); 
		if (projectTaskArray.size() > 0){
				for (ProjectTask x: projectTaskArray ){
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM-dd");
					String projectTaskStartDate = "";
					String projectTaskEndDate  = "";
					if (x.getTaskStartDate() != null){
						projectTaskStartDate = simpleDateFormat.format(x.getTaskStartDate());
						projectTaskEndDate = simpleDateFormat.format(x.getTaskEndDate());
					}
					String taskDependencyString = "";
					if (x.getTaskDependency().size() > 0){
						for (int taskDependencyInt : x.getTaskDependency()){
							taskDependencyString += taskDependencyInt + ", ";
						}
						taskDependencyString = taskDependencyString.substring(0, (taskDependencyString.length()-2));
					}

					String taskId = Integer.toString(x.getProjectTaskId());
					projectTaskString2.append(taskId);
					
					if((taskIdMaxLen - taskId.length()) > 0){
						int diff = taskIdMaxLen - taskId.length();
						for(int y = 0; y < diff; y++){
							projectTaskString2.append(' ');
						}
					}

					for(int y = 0; y < columnSpace; y++){
						projectTaskString2.append(' ');	 		
					}
					
					projectTaskString2.append(projectTaskStartDate);

					if((startDateLen - projectTaskStartDate.length()) > 0){
						int diff = startDateLen - projectTaskStartDate.length();
						for(int y = 0; y < diff; y++){
							projectTaskString2.append(' ');
						}
					}

					for(int y = 0; y < columnSpace; y++){
						projectTaskString2.append(' ');	 		
					}

					projectTaskString2.append(projectTaskEndDate);

					if((endDateLen - projectTaskEndDate.length()) > 0){
						int diff = endDateLen - projectTaskEndDate.length();
						for(int y = 0; y < diff; y++){
							projectTaskString2.append(' ');
						}
					}

					for(int y = 0; y < columnSpace; y++){
						projectTaskString2.append(' ');	 		
					}

					String duration = Integer.toString(x.getTaskDuration());
					projectTaskString2.append(duration);
					
					if((durationLen - duration.length()) > 0){
						int diff = durationLen - duration.length();
						for(int y = 0; y < diff; y++){
							projectTaskString2.append(' ');
						}
					}

					for(int y = 0; y < columnSpace; y++){
						projectTaskString2.append(' ');	 		
					}
				
					projectTaskString2.append(x.getProjectTaskName());

					if((taskNameLen - x.getProjectTaskName().length()) > 0){
						int diff = taskNameLen - x.getProjectTaskName().length();
						for(int y = 0; y < diff; y++){
							projectTaskString2.append(' ');
						}
					}

					for(int y = 0; y < columnSpace; y++){
						projectTaskString2.append(' ');	 	
					}
					
					projectTaskString2.append(taskDependencyString);
					projectTaskString2.append(System.lineSeparator());
				}
		}
		//================================================================
		this.projectTaskBody = projectTaskString1 + projectTaskString + projectTaskString2;
		
		int maxLenTotal = taskIdMaxLen + startDateLen + endDateLen + durationLen + taskNameLen + dependenciesLen + 5*columnSpace;
		for (int y = 0; y < maxLenTotal; y ++){
			this.firstLastLine += '-';			
		}
	}

	private void layoutProjectPlan(){
		StringBuilder projectPlanString = new StringBuilder();
		projectPlanString.append("# Project Plan for " + projectPlan.getProjectPlanName() + System.lineSeparator());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM-dd");
		String projectStartDate = "";
		String projectEndDate = "";
		projectStartDate = simpleDateFormat.format(projectPlan.getProjectTargetStartDate());
		projectEndDate = simpleDateFormat.format(projectPlan.getProjectTargetEndDate());
		projectPlanString.append("# Target Start Date: " + projectStartDate  + System.lineSeparator());
		projectPlanString.append("# Target End Date: " + projectEndDate + System.lineSeparator());
		this.projectPlanBody = projectPlanString.toString();
	}
}
