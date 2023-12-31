package com.academy.EMSExam.fileoperations;

import com.academy.EMSExam.dto.LineDTO;
import com.academy.EMSExam.utils.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements CustomReader {

	@Override
	public List<? extends Serializable> read(String filename) {
		List<LineDTO> employeeRecord = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if (HelperMethods.isInt(values[0]) == false) {
					HelperMethods.hasHeaders = true;
					HelperMethods.headers = line;
				}

				if (HelperMethods.isInt(values[0])) {
					int rowAdd = 1;
					if (HelperMethods.hasHeaders) {
						rowAdd = 2;
					}
					try {
						int empId = Integer.parseInt(values[0]);
						int projectId = Integer.parseInt(values[1]);
						LocalDate dateFrom = CustomDateFormatter.parseDate((values[2]));
						LocalDate dateTo = CustomDateFormatter.parseDate(HelperMethods.nullStringConverter(values[3]));
						employeeRecord.add(new LineDTO(empId, projectId, dateFrom, dateTo));
					} catch (DateTimeParseException e) {
						System.out.printf("Date format parse not possible on row %d, starting with id %s%n", employeeRecord.size() + rowAdd, values[0]);
						System.out.println("Check if entered data is a valid date");
						System.out.println("For supported date formats check in the readme file.");
						break;
					} catch (Exception e) {
						System.out.printf("Date format not supported on row %d, starting with id %s%n", employeeRecord.size() + rowAdd, values[0]);
						System.out.println("For supported date formats check in the readme file.");
						break;
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return employeeRecord;
	}
}


