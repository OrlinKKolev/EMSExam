package com.academy.EMSExam;

import com.academy.EMSExam.fileOperations.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainTest {
	public static void main(String[] args) {
		//reader part
		CustomReader reader = new CSVReader();
		var resources = reader.read("./src/main/resources/files/generated_data.csv");
		System.out.println(resources);
		//writer part
		List<Serializable> parsedData = new ArrayList<>();
		CustomWriter writer = new CSVWriter();
		parsedData.addAll(resources);
		writer.write(resources, "./src/main/resources/files/parsed_data.csv");
		System.out.println(parsedData);
		System.out.println(resources.get(0));
	}

}
