package com.academy.EMSExam.dto;

import java.io.Serializable;
import java.time.LocalDate;


public class LineDTO implements Serializable {
	private int empId;
	private int projectId;
	private LocalDate dateFrom;
	private LocalDate dateTo;

	public LineDTO(int empId, int projectId, LocalDate dateFrom, LocalDate dateTo) {
		this.empId = empId;
		this.projectId = projectId;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	public int getEmpId() {
		return empId;
	}

	public int getProjectId() {
		return projectId;
	}

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public LocalDate getDateTo() {
		return dateTo;
	}

	@Override
	public String toString() {

		return this.empId + "," + this.projectId + "," + this.dateFrom.toString() + "," + this.dateTo.toString();
	}
}
