package com.academy.EMSExam.model;

import java.time.LocalDate;
import java.time.Period;


public class Pair {
	public int getPairid() {
		return pairId;
	}
	private int pairId;
	private int projectId;
	private int employeeId1;
	private LocalDate startDate1;
	private LocalDate endDate1;
	private int employeeId2;
	private LocalDate startDate2;
	private LocalDate endDate2;

	public Pair(int projectId, int employeeId1, LocalDate startDate1, LocalDate endDate1, int employeeId2, LocalDate startDate2, LocalDate endDate2) {
		this.projectId = projectId;
		this.employeeId1 = employeeId1;
		this.startDate1 = startDate1;
		this.endDate1 = endDate1;
		this.employeeId2 = employeeId2;
		this.startDate2 = startDate2;
		this.endDate2 = endDate2;
	}

	public int period() {
		LocalDate periodStart = null;
		if (startDate1.isBefore(startDate2)) {
			periodStart = startDate2;
		} else periodStart = startDate1;

		LocalDate periodEnd = null;
		if (endDate1.isAfter(endDate2)) {
			periodEnd = endDate2;
		} else periodEnd = endDate1;
		Period period = Period.between(periodStart, periodEnd);
		return period.getDays() + 1; //Period function returns -1 day
	}
	
}
