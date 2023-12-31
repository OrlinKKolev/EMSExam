package com.academy.EMSExam.model;

import java.time.Duration;
import java.time.LocalDate;


public class Pair {
	private final int pairId;
	public int getProjectId() {
		return projectId;
	}
	private final int projectId;
	private final int employeeId1;
	private LocalDate startDate1;
	private LocalDate endDate1;
	private final int employeeId2;
	private LocalDate startDate2;
	private LocalDate endDate2;
	public String key (){
		String text1= String.valueOf(employeeId1);
		String text2= String.valueOf(employeeId2);
		return text1+"|"+text2;
	}
	public Pair(int pairId, int projectId, int employeeId1, LocalDate startDate1, LocalDate endDate1, int employeeId2, LocalDate startDate2, LocalDate endDate2) {
		this(pairId, projectId, employeeId1, employeeId2);
		this.startDate1 = startDate1;
		this.endDate1 = endDate1;
		this.startDate2 = startDate2;
		this.endDate2 = endDate2;
	}
	public Pair(int pairId, int projectId, int employeeId1, int employeeId2) {
		this.pairId = pairId;
		this.projectId = projectId;
		this.employeeId1 = employeeId1;
		this.employeeId2 = employeeId2;
	}
	public void setStartDate1(LocalDate startDate1) {
		this.startDate1 = startDate1;
	}
	public void setEndDate1(LocalDate endDate1) {
		this.endDate1 = endDate1;
	}
	public void setStartDate2(LocalDate startDate2) {
		this.startDate2 = startDate2;
	}
	public void setEndDate2(LocalDate endDate2) {
		this.endDate2 = endDate2;
	}
	public long calcDuration() {
		LocalDate periodStart;
		if (startDate1.isBefore(startDate2)) {
			periodStart = startDate2;
		} else periodStart = startDate1;

		LocalDate periodEnd;
		if (endDate1.isAfter(endDate2)) {
			periodEnd = endDate2;
		} else periodEnd = endDate1;

		Duration duration = Duration.between(periodStart.atStartOfDay(), periodEnd.atStartOfDay());
		long durationInDays = duration.toDays() + 1; //Duration function returns -1 day
		if (durationInDays < 1) {
			return 0;
		} else return durationInDays;
	}

}
