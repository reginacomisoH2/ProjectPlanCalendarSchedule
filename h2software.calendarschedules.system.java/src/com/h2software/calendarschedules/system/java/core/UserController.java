package com.h2software.calendarschedules.system.java.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.h2software.calendarschedules.system.java.beans.ProjectPlan;
import com.h2software.calendarschedules.system.java.beans.ProjectTask;

public class UserController {
	//handles the process flow
	/* PROCESS FLOW:
	 * 1. Enter Project Plan Details
	 * 2. Add Tasks as required. Enter task details.
	 * 3. Calculate start and end date of each task after all tasks are entered.
	 * 4. Show report.
	 */
	//prints the report at console
	public static void main(String [] args) {

		//setupProjectPlan
		ProjectPlanProcessor projectPlanProcessor= new ProjectPlanProcessor();
		projectPlanProcessor.setupProjectPlan();
		ProjectPlan projectPlan = projectPlanProcessor.reviewProjectPlanDetails();

		//create task/s
		//get entered value
		String createNewTask = "";
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		ArrayList<ProjectTask> projectTaskArray = new ArrayList<ProjectTask>();
		while(createNewTask.isEmpty() || createNewTask.equals("Y")){
			System.out.println("Create new task (Y/N)? ");
			try {
				createNewTask = br.readLine().toUpperCase();
				if (!createNewTask.equals("Y") && !createNewTask.equals("N")) {
					createNewTask = "";
				}
				if (createNewTask.equals("Y")){
					ProjectTaskProcessor projectTaskProcessor = new ProjectTaskProcessor();
					projectTaskProcessor.setupTask(projectTaskArray, projectPlan);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

			ReportGenerator reportGenerator = new ReportGenerator(projectPlan, projectTaskArray);
			reportGenerator.generateReport();
		
	}
}
