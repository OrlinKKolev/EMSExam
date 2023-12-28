package com.academy.EMSExam.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Pair {
	private int pairId;
	private int projectId;
	private int employeeId1;
	private LocalDate startDate1;
	private LocalDate endDate1;
	private int employeeId2;
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
		this.pairId=pairId;
		this.projectId = projectId;
		this.employeeId1 = employeeId1;
		this.employeeId2 = employeeId2;
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
		long durationInDays = duration.toDays()+1; //Duration function returns -1 day
		if (durationInDays < 1) {
			return 0;
		} else return durationInDays;
	}

}
