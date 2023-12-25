package com.academy.EMSExam.dto;

import java.io.Serializable;
import java.time.LocalDate;


public class LineDTO implements Serializable {
	private int empId;
	private int projectId;
	private String dateFrom;
	private String dateTo;

	public LineDTO(int empId, int projectId, LocalDate dateFrom, LocalDate dateTo) {
		this.empId = empId;
		this.projectId = projectId;
		this.dateFrom = String.valueOf(dateFrom);
		this.dateTo = String.valueOf(dateTo);
	}

	public int getEmpId() {
		return empId;
	}

	public int getProjectId() {
		return projectId;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	@Override
	public String toString() {

		return this.empId + "," + this.projectId + "," + this.dateFrom + "," + this.dateTo;
	}
}
