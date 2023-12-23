package com.academy.EMSExam;

import com.academy.EMSExam.fileOperations.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainTest {
	public static void main(String[] args) {
		List<Serializable> records;
		records = new ArrayList<>();
//		CustomWriter writer = new CSVWriter();
		CustomReader reader = new CSVReader();
		var resources = reader.read("./src/main/resources/files/generated_data.csv");
//		System.out.println(records);
		System.out.println(resources);
//		writer.write(employees, "./src/main/resources/files/parsed_data.csv");
	}

}
