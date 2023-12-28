package com.academy.EMSExam.repository;

import com.academy.EMSExam.fileOperations.CSVReader;
import com.academy.EMSExam.fileOperations.CustomReader;

import java.io.Serializable;
import java.util.List;

public class CSVRepository {

	public static List<? extends Serializable> read(String filepath) {
		CustomReader reader = new CSVReader();
		return reader.read(filepath);
	}

}
