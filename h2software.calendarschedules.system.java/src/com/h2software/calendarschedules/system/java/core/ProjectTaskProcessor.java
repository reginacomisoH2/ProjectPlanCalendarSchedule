package com.h2software.calendarschedules.system.java.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import com.h2software.calendarschedules.system.java.beans.ProjectPlan;
import com.h2software.calendarschedules.system.java.beans.ProjectTask;

public class ProjectTaskProcessor {
	private ProjectTask projectTask;

	public void setupTask(ArrayList<ProjectTask> projectTaskArray, ProjectPlan projectPlan) {
		projectTask = new ProjectTask();
		System.out.println("====================================================");
		System.out.println("CREATING NEW TASK...");
		inputTaskId(projectTaskArray);
		inputProjectTaskName();
		inputTaskDuration();
		ProjectTaskDependencyProcessor projectTaskDependencyProcessor = new ProjectTaskDependencyProcessor();
		ArrayList<Integer> taskDependency = projectTaskDependencyProcessor.createTaskDependency(projectTaskArray);
		projectTask.setTaskDependency(taskDependency);
		calculateDates(projectTaskArray, projectPlan);
		//projectTask.setTaskDependencyHolder((ArrayList<Integer>) taskDependency.clone());
		projectTaskArray.add(projectTask);
		Collections.sort(projectTaskArray);
		System.out.println("New project task created. Task Id: " + projectTask.getProjectTaskId() + " || Task Name: " + projectTask.getProjectTaskName() + " || Dependency Count: " + projectTask.getTaskDependency().size());
		System.out.println("====================================================");
		}
	private void calculateDates(ArrayList<ProjectTask> projectTaskArray, ProjectPlan projectPlan) {

		if (projectTask.getTaskDependency().size() == 0) {
			//Task Start Date = Project Start Date
			projectTask.setTaskStartDate(projectPlan.getProjectTargetStartDate());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(projectTask.getTaskStartDate());
			calendar.add(Calendar.DATE, projectTask.getTaskDuration());
			projectTask.setTaskEndDate(calendar.getTime());
		}
		else if (projectTask.getTaskDependency().size() > 0){
			//check latest date that can be assigned to this task's start date
			//initialize ~ earliest date is project start date
			Date tempTaskStartDate = projectPlan.getProjectTargetStartDate();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(tempTaskStartDate);
			calendar.add(Calendar.DATE, projectTask.getTaskDuration());
			Date tempTaskEndDate = calendar.getTime();

			//iterate on task Dependency array of this task
			for(int taskId : projectTask.getTaskDependency()) {
				//iterate on task Array (all available tasks created)
				for(ProjectTask projectTask2 : projectTaskArray){
					if (projectTask2.getProjectTaskId() == taskId){
						if (projectTask2.getTaskEndDate().compareTo(tempTaskStartDate) > 0) {
							tempTaskStartDate = projectTask2.getTaskEndDate();
							calendar.setTime(tempTaskStartDate);
							calendar.add(Calendar.DATE, projectTask.getTaskDuration());
							tempTaskEndDate = calendar.getTime();
						}
					}
				}
			}
			projectTask.setTaskStartDate(tempTaskStartDate);
			projectTask.setTaskEndDate(tempTaskEndDate);
		}
	}
	/*
				//next find all tasks linked with this task.set start and end date of all linked tasks
				for(ProjectTask projectTask2 : projectTaskArray){
					//projectTask task id is in projectTask2 dependency
					if (projectTask2.getTaskDependencyHolder().size() > 0){
						if (projectTask2.getTaskDependencyHolder().contains(projectTask.getProjectTaskId())){
							//this will delete the taskDependency at taskDependencyHolder ~ this array is just used for processing for later
							projectTask2.getTaskDependencyHolder().remove(Integer.valueOf(projectTask.getProjectTaskId()));
							//assign startDate & end date
							if(projectTask2.getTaskStartDate() == null) {
								projectTask2.setTaskStartDate(projectTask.getTaskEndDate());
								Calendar calendar2 = Calendar.getInstance();
								calendar2.setTime(projectTask2.getTaskStartDate());
								calendar2.add(Calendar.DATE, projectTask2.getTaskDuration());
								projectTask2.setTaskEndDate(calendar2.getTime());
							}
							else{
								//compare, and use date which is later than the other date..
								if (projectTask.getTaskEndDate().compareTo(projectTask2.getTaskStartDate()) > 0){
									projectTask2.setTaskStartDate(projectTask.getTaskEndDate());
									Calendar calendar2 = Calendar.getInstance();
									calendar2.setTime(projectTask2.getTaskStartDate());
									calendar2.add(Calendar.DATE, projectTask2.getTaskDuration());
									projectTask2.setTaskEndDate(calendar2.getTime());
								}
							}
						}
					}
				}*/

	private void inputTaskId(ArrayList<ProjectTask> projectTaskArray){
		//get entered value and validate
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		String taskId = "";
		int taskIdInt = 0;
		while (taskId.isEmpty()){
			System.out.print("Enter Task Id: ");
			try {
				taskId = br.readLine();
				taskIdInt = Integer.parseInt(taskId);
				if (taskIdInt <=0) {
					System.out.println("Task Id must be a positive integer. ");
					taskId = "";
				}		

				ProjectTask projectTask = HelperUtils.validateTaskIdExists(taskIdInt, projectTaskArray);

				if (projectTask != null){
					System.out.println("Task Id already exists. Please use a different Id for this task.");
					taskId = "";
				}

			} catch (NumberFormatException nfe){
				System.out.println("Task Id must be a positive integer. ");
				taskId = "";				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		projectTask.setProjectTaskId(taskIdInt);
	}

	private void inputProjectTaskName(){
		//get entered value and validate
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		String projectTaskName = "";
		while (projectTaskName.isEmpty()){
			System.out.print("Enter Task Name: ");
			try {
				projectTaskName = br.readLine();
				if (projectTaskName.isEmpty()){
					System.out.println("Task Name cannot be blank. ");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		projectTask.setProjectTaskName(projectTaskName);		
	}
	private void inputTaskDuration(){
		//get entered value and validate
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		String taskDuration = "";
		int taskDurationInt = 0;
		while (taskDuration.isEmpty()){
			System.out.print("Enter Task Duration in Days: ");
			try {
				taskDuration = br.readLine();
				taskDurationInt = Integer.parseInt(taskDuration);
				if (taskDurationInt <=0) {
					System.out.println("Task duration must be a positive integer. ");
					taskDuration = "";
				}				
			} catch (NumberFormatException nfe){
				System.out.println("Task duration must be a positive integer. ");
				taskDuration = "";				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		projectTask.setTaskDuration(taskDurationInt);
	}

}
