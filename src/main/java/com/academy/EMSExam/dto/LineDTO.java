package com.academy.EMSExam.dto;

import java.io.Serializable;


public class LineDTO implements Serializable {
	int empId;
	int projectId;
	String dateFrom;
	String dateTo;

	public LineDTO(int empId, int projectId, String dateFrom, String dateTo) {
		this.empId = empId;
		this.projectId = projectId;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}
	@Override
	public String toString() {

		return this.empId + "," + this.projectId + "," + this.dateFrom + "," + this.dateTo;
	}
}
