package com.academy.EMSExam.dto;

import java.io.Serializable;
import java.util.Date;

public class lineDTO implements Serializable {
	int empId;
	int projectId;
	String dateFrom;
	String dateTo;

	public lineDTO(int empId, int projectId, String dateFrom, String dateTo) {
		this.empId = empId;
		this.projectId = projectId;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}
}
