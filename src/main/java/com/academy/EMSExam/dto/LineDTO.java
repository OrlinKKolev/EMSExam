package com.academy.EMSExam.dto;

import java.io.Serializable;
import java.time.LocalDate;


public class LineDTO implements Serializable {
	int empId;
	int projectId;
	String dateFrom;
	String dateTo;

	public LineDTO(int empId, int projectId, LocalDate dateFrom, LocalDate dateTo) {
		this.empId = empId;
		this.projectId = projectId;
		this.dateFrom = String.valueOf(dateFrom);
		this.dateTo = String.valueOf(dateTo);
	}
	@Override
	public String toString() {

		return this.empId + "," + this.projectId + "," + this.dateFrom + "," + this.dateTo;
	}
}
