package com.academy.EMSExam.fileOperations;


import com.academy.EMSExam.dto.lineDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements CustomReader {


	@Override
	public List<? extends Serializable> read(String filename) {
		List<lineDTO> record = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				record.add(new lineDTO(Integer.parseInt(values[0]),
						Integer.parseInt(values[1]),
						values[2],
						values[3]));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return record;
	}
}


//				if (HelperMethods.isInt(values[0])) {
//						//  TODO validations for input data
//						employees.add(new Employee(Integer.parseInt(values[0]),
//						values[1],
//						LocalDate.parse(values[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
////							LocalDate.parse(values[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
//						Department.valueOf(values[4]),
//						values[5],
//						Double.parseDouble(values[6])));
//						} else {
//						br.readLine();
//						}