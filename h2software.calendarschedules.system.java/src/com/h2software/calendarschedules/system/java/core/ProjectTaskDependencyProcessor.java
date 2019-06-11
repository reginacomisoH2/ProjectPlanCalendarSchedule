package com.h2software.calendarschedules.system.java.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.h2software.calendarschedules.system.java.beans.ProjectTask;

public class ProjectTaskDependencyProcessor {
	ArrayList<Integer> taskDependencyArray = new ArrayList<Integer>();

	public ArrayList<Integer> createTaskDependency(ArrayList<ProjectTask> projectTaskArray){	
		String isTaskDependency = "";
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		String cancelTaskDependency = "";

		while((isTaskDependency.isEmpty() || isTaskDependency.equals("Y")) && !cancelTaskDependency.equals("Y")){
			System.out.println("====================================================");
			System.out.println("Add Task Dependency (Y/N)? ");
			try {
				isTaskDependency = br.readLine().toUpperCase();
				if (!isTaskDependency.equals("Y") && !isTaskDependency.equals("N")) {
					isTaskDependency = "";
				}
				if (isTaskDependency.equals("Y")){
					cancelTaskDependency = inputTaskDependency(projectTaskArray);	
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return taskDependencyArray;
	}

	private String inputTaskDependency(ArrayList<ProjectTask> projectTaskArray){	
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		String taskDependencySingle = "";
		int taskDependencySingleInt = 0;
		String cancelTaskDependency = "";
		while (taskDependencySingle.isEmpty()){
			System.out.println("====================================================");
			System.out.println("Enter the Task Id this task is depending: ");	
			try {
				taskDependencySingle = br.readLine();
				taskDependencySingleInt = Integer.parseInt(taskDependencySingle);
				if (taskDependencySingleInt <=0) {
					System.out.println("Task Id must be a positive integer. ");
					taskDependencySingle = "";
				}				

				ProjectTask projectTask = HelperUtils.validateTaskIdExists(taskDependencySingleInt, projectTaskArray);
				if (projectTask == null){
					System.out.println("Task Id specified is not yet created. Please create the task before adding it as a dependency. Cancel adding Task Dependency? (Y/N) ");	

					while(cancelTaskDependency.isEmpty() || cancelTaskDependency.equals("N")){
						cancelTaskDependency = br.readLine().toUpperCase();
						if (!cancelTaskDependency.equals("Y") && !cancelTaskDependency.equals("N")) {
							System.out.println("Cancel adding Task Dependency? (Y/N) ");
							cancelTaskDependency = "";
						}
						if (cancelTaskDependency.equals("N")){		
							taskDependencySingle = "";
							break;
						}
					}
				}
				if (projectTask != null){
					boolean isTaskDependencyAlreadyAdded = validateTaskDependencyAlreadyAdded(taskDependencySingleInt);
					if (isTaskDependencyAlreadyAdded) {
						System.out.println("Specified task was already previously added as Task Dependency. Cancel adding Task Dependency? (Y/N) ");
						while(cancelTaskDependency.isEmpty() || cancelTaskDependency.equals("N")){
							cancelTaskDependency = br.readLine().toUpperCase();
							if (!cancelTaskDependency.equals("Y") && !cancelTaskDependency.equals("N")) {
								System.out.println("Cancel adding Task Dependency? (Y/N) ");
								cancelTaskDependency = "";
							}
							if (cancelTaskDependency.equals("N")){		
								taskDependencySingle = "";
								break;
							}
						}
					}
					if (!isTaskDependencyAlreadyAdded){
						taskDependencyArray.add(taskDependencySingleInt);
						System.out.println("Dependency added. TaskId: " + projectTask.getProjectTaskId() + " TaskName: " + projectTask.getProjectTaskName());
						//System.out.println(taskDependencyArray.size());
					}
				}				
			} catch (NumberFormatException nfe){
				System.out.println("Task Id must be a positive integer. ");
				taskDependencySingle = "";				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cancelTaskDependency;
	}

	private boolean validateTaskDependencyAlreadyAdded(int taskDependencySingleInt){
		boolean isTaskDependencyAlreadyAdded = false;
		if (taskDependencyArray != null) {
			for (int x: taskDependencyArray) {
				if (x == taskDependencySingleInt) {
					isTaskDependencyAlreadyAdded = true;
					break;
				}
			}
		}
		return isTaskDependencyAlreadyAdded;
		
	}
}
