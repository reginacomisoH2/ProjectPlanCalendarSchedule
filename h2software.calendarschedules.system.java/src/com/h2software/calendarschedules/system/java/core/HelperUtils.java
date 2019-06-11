package com.h2software.calendarschedules.system.java.core;

import java.util.ArrayList;

import com.h2software.calendarschedules.system.java.beans.ProjectTask;

public class HelperUtils {
	private HelperUtils(){
		
	}

	public static ProjectTask validateTaskIdExists(int taskId, ArrayList<ProjectTask> projectTaskArray){
		ProjectTask projectTask = null;
		if (projectTaskArray != null) {
			for (ProjectTask x: projectTaskArray) {
				if (x.getProjectTaskId() == taskId) {
					projectTask = x;
					break;
				}
			}
		}
		return projectTask;
	}
}
