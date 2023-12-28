package com.academy.EMSExam.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Pair {
	private final int pairId;
	private final int projectId;
	private final int employeeId1;
	private LocalDate startDate1;
	private LocalDate endDate1;
	private final int employeeId2;
	private LocalDate startDate2;
	private LocalDate endDate2;

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

	public LocalDate getStartDate1() {
		return startDate1;
	}

	public void setStartDate1(LocalDate startDate1) {
		this.startDate1 = startDate1;
	}

	public LocalDate getEndDate1() {
		return endDate1;
	}

	public void setEndDate1(LocalDate endDate1) {
		this.endDate1 = endDate1;
	}

	public LocalDate getStartDate2() {
		return startDate2;
	}

	public void setStartDate2(LocalDate startDate2) {
		this.startDate2 = startDate2;
	}

	public LocalDate getEndDate2() {
		return endDate2;
	}

	public void setEndDate2(LocalDate endDate2) {
		this.endDate2 = endDate2;
	}

	public int getProjectId() {
		return projectId;
	}

	public int getPairid() {
		return pairId;
	}

	public long duration() {
		LocalDate periodStart = null;
		if (startDate1.isBefore(startDate2)) {
			periodStart = startDate2;
		} else periodStart = startDate1;

		LocalDate periodEnd = null;
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
