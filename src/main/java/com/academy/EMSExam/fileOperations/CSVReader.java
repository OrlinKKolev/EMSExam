package com.academy.EMSExam.fileOperations;


import com.academy.EMSExam.dto.LineDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements CustomReader {


	@Override
	public List<? extends Serializable> read(String filename) {
		List<LineDTO> record = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				record.add(new LineDTO(Integer.parseInt(values[0]),
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
