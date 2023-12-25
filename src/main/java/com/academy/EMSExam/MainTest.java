package com.academy.EMSExam;

import com.academy.EMSExam.dto.LineDTO;
import com.academy.EMSExam.fileOperations.*;

import java.io.Serializable;
import java.util.*;

public class MainTest {
	public static void main(String[] args) {
		//reader part
		CustomReader reader = new CSVReader();
		var resources = reader.read("./src/main/resources/files/generated_data.csv");
		System.out.println(resources);
		List<Integer> employees = new ArrayList<>();
		List<Integer> projects = new ArrayList<>();

		//crate lists with unique values. I use List instead of Set, because I want to get values by index
		for (Serializable resource : resources) {
			LineDTO dto = (LineDTO) resource;
			if (!employees.contains(dto.getEmpId())) {
				employees.add(dto.getEmpId());
			}
			if (!projects.contains(dto.getProjectId())) {
				projects.add(dto.getProjectId());
			}
		}
		//List with all employees in the records
		System.out.println(employees);
		//List with all projects in the records
		System.out.println(projects);

		//loops through all items and put in list with unique EmpIDs
		HashMap<Integer, Set<Integer>> projectsWithPairs = new HashMap<>();
		int projectIdMatcher = 1;
		for (int i = 0; i < projects.size(); i++) {
			Set<Integer> employeesPerProject = new HashSet<>();
			//
			for (Serializable resource : resources) {
				LineDTO dto = (LineDTO) resource;
				if (dto.getProjectId() == projectIdMatcher) {
					employeesPerProject.add(dto.getEmpId());
				}
			}
			System.out.printf("ProjID:%d, EmpID: %s %n",projectIdMatcher,employeesPerProject);
			projectsWithPairs.put(projectIdMatcher, employeesPerProject);
			projectIdMatcher++;
		}
		System.out.println(projectsWithPairs);



//		//writer part
//		List<Serializable> parsedData = new ArrayList<>();
//		CustomWriter writer = new CSVWriter();
//		parsedData.addAll(resources);
//		writer.write(resources, "./src/main/resources/files/parsed_data.csv");
//		System.out.println(parsedData);

//
//		for (Serializable el:resources) {
//			String[] result= el.toString().split(",");
//			employees.add(Integer.parseInt(result[0]));
//		}
//		System.out.println(employees);
//
//		Set<Integer> projects = new HashSet<>();
//		for (Serializable el:resources) {
//			String[] result= el.toString().split(",");
//			projects.add(Integer.parseInt(result[1]));
//		}
//		System.out.println(projects);


	}

}
