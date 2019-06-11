package com.h2software.calendarschedules.system.java.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.h2software.calendarschedules.system.java.beans.ProjectPlan;

public class ProjectPlanProcessor {
	private ProjectPlan projectPlan;

	public void setupProjectPlan() {
		projectPlan = new ProjectPlan();
		inputProjectPlanName();
		inputProjectTargetStartDate();
		inputProjectTargetEndDate();
		calculateProjectDurationInDays();
	}

	public ProjectPlan reviewProjectPlanDetails() {
		System.out.println("====================================================");
		System.out.println("Project Plan created.");	
		/*
		System.out.println("Name: " + projectPlan.getProjectPlanName());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String projectTargetStartDate = simpleDateFormat.format(projectPlan.getProjectTargetStartDate());
		String projectTargetEndDate = simpleDateFormat.format(projectPlan.getProjectTargetEndDate());
		
		System.out.println("Target Start Date: " + projectTargetStartDate);
		System.out.println("Project End Date: " + projectTargetEndDate);
		
		int projectDuration = (int) projectPlan.getDurationInDays();
		if (projectDuration == 1){
			System.out.println("Project Duration: " + (int) projectPlan.getDurationInDays() + " day");			
		} else {
			System.out.println("Project Duration: " + (int) projectPlan.getDurationInDays() + " days");
		}
		*/
		System.out.println("====================================================");
		return projectPlan;
	}
	private void inputProjectPlanName(){
		//get entered value and validate
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		String projectPlanName = "";
		while (projectPlanName.isEmpty()){
			System.out.print("Enter Project Plan Name: ");
			try {
				projectPlanName = br.readLine();
				if (projectPlanName.isEmpty()){
					System.out.println("Project Plan Name cannot be blank. ");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		projectPlan.setProjectPlanName(projectPlanName);
	}

	private void inputProjectTargetStartDate(){
		//get entered value and validate
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		String projectTargetStartDate = "";
		Date targetStartDate = null;

		while (projectTargetStartDate.isEmpty()){
			System.out.print("Enter Target Start Date (MM/DD/YYYY): ");
			try {
				projectTargetStartDate = br.readLine();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
				simpleDateFormat.setLenient(false);
				targetStartDate = simpleDateFormat.parse(projectTargetStartDate);
			} catch (ParseException e) {
				System.out.println("Incorrect Target Start Date format. Please enter date using format MM/DD/YYYY. ");
				projectTargetStartDate = "";
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		projectPlan.setProjectTargetStartDate(targetStartDate);
		//System.out.println(projectPlan.getProjectTargetStartDate());
	}

	private void inputProjectTargetEndDate(){
		//get entered value and validate
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		String projectTargetEndDate = "";
		Date targetEndDate = null;

		while (projectTargetEndDate.isEmpty()){
			System.out.print("Enter Target End Date (MM/DD/YYYY): ");
			try {
				projectTargetEndDate = br.readLine();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
				simpleDateFormat.setLenient(false);
				targetEndDate = simpleDateFormat.parse(projectTargetEndDate);
			} catch (ParseException e) {
				System.out.println("Incorrect Target End Date format. Please enter date using format MM/DD/YYYY. ");
				projectTargetEndDate = "";
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		projectPlan.setProjectTargetEndDate(targetEndDate);
		//System.out.println(projectPlan.getProjectTargetEndDate());
	}
	
	private void calculateProjectDurationInDays() {
		int x = projectPlan.getProjectTargetEndDate().compareTo(projectPlan.getProjectTargetStartDate());
		while (x <0){
			System.out.println("Project Target Start Date cannot be later than Project Target End Date.");
			inputProjectTargetStartDate();
			inputProjectTargetEndDate();
			x = projectPlan.getProjectTargetEndDate().compareTo(projectPlan.getProjectTargetStartDate());

		}
		long diff = (projectPlan.getProjectTargetEndDate().getTime() - projectPlan.getProjectTargetStartDate().getTime()) + 1;
		long projectDurationInDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
		projectPlan.setDurationInDays(projectDurationInDays);		
		//System.out.println ("Project Duration Days: " + projectDurationInDays);
	}
}

