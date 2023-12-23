package com.academy.EMSExam.fileOperations;

import com.academy.EMSExam.dto.LineDTO;
import com.academy.EMSExam.utils.CustomDateFormatter;
import com.academy.EMSExam.utils.HelperMethods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements CustomReader {

	@Override
	public List<? extends Serializable> read(String filename) {
		List<LineDTO> employees = new ArrayList<>();
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
						employees.add(new LineDTO(Integer.parseInt(values[0]),
								Integer.parseInt(values[1]),
								CustomDateFormatter.parseDate(values[2]),
								CustomDateFormatter.parseDate(values[3])));
					} catch (DateTimeParseException e) {
						System.out.printf("Date format parse not possible row %d, starting with id %s%n", employees.size() + rowAdd, values[0]);
						System.out.println("Check if entered data is a valid date");
						System.out.println("For supported date formats check in the readme file.");

						break;
					} catch (Exception e) {
						System.out.printf("Date format not supported on row %d, starting with id %s%n", employees.size() + rowAdd, values[0]);
						System.out.println("For supported date formats check in the readme file.");

					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return employees;
	}
}


