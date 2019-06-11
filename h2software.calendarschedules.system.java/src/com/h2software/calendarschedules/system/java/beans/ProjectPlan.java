package com.h2software.calendarschedules.system.java.beans;

import java.util.Date;

public class ProjectPlan {
	private String projectPlanName;
	private Date projectTargetStartDate;
	private Date projectTargetEndDate;
	private long durationInDays;
	
	public String getProjectPlanName() {
		return projectPlanName;
	}
	public void setProjectPlanName(String projectPlanName) {
		this.projectPlanName = projectPlanName;
	}
	public Date getProjectTargetStartDate() {
		return projectTargetStartDate;
	}
	public void setProjectTargetStartDate(Date projectTargetStartDate) {
		this.projectTargetStartDate = projectTargetStartDate;
	}
	public Date getProjectTargetEndDate() {
		return projectTargetEndDate;
	}
	public void setProjectTargetEndDate(Date projectTargetEndDate) {
		this.projectTargetEndDate = projectTargetEndDate;
	}
	public long getDurationInDays() {
		return durationInDays;
	}
	public void setDurationInDays(long durationInDays) {
		this.durationInDays = durationInDays;
	}

}
