package com.academy.EMSExam;

import com.academy.EMSExam.dto.LineDTO;
import com.academy.EMSExam.model.Pair;
import com.academy.EMSExam.repository.CSVRepository;
import com.academy.EMSExam.utils.Globals;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class MainTest {
	public static void main(String[] args) {

		var resources = CSVRepository.read("./src/main/resources/files/generated_data.csv");
		var employees = listAllEmployees(resources);
		var projects = listAllProjects(resources);
		var projectsByParis = listProjectsByPairs(resources,projects);

		System.out.println(resources);
		System.out.println(employees);
		System.out.println(projects);
		System.out.println(projectsByParis);


		Pair pair1 = new Pair(Globals.counter,5, 102, LocalDate.parse("2022-10-01"), LocalDate.parse("2023-10-01"),
										103, LocalDate.parse("2023-12-01"), LocalDate.parse("2024-05-01"));
		Globals.counter++;
		System.out.println(pair1.duration());
		Pair pair2 = new Pair(Globals.counter,5, 102, LocalDate.parse("2022-10-01"), LocalDate.parse("2023-12-31"),
											103, LocalDate.parse("2023-12-01"), LocalDate.parse("2024-05-01"));
		Globals.counter++;
		System.out.println(pair2.duration());
		Pair pair3 = new Pair(Globals.counter,5, 102, LocalDate.parse("2022-10-01"), LocalDate.parse("2023-12-31"),
										103, LocalDate.parse("2022-10-01"), LocalDate.parse("2023-12-31"));
		Globals.counter++;
		System.out.println(pair3.duration());
		Pair pair4 = new Pair(Globals.counter,5, 102, LocalDate.parse("2022-10-01"), LocalDate.parse("2022-10-01"),
				103, LocalDate.parse("2022-10-01"), LocalDate.parse("2022-10-01"));
		Globals.counter++;
		System.out.println(pair4.duration());
		System.out.println(pair4.getPairid());

		for (int i = 0; i < projects.size(); i++) {
			System.out.println(projectsByParis.get(projects.get(i)));
			for (int j = 0; j < projectsByParis.size(); j++) {

			}

		}



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

	public static List<Integer> listAllEmployees(List<? extends Serializable> resources) {
		//crate lists with unique values. Use List instead of Set, because I want to get values by index
		List<Integer> employees = new ArrayList<>();
		for (Serializable resource : resources) {
			LineDTO dto = (LineDTO) resource;
			if (!employees.contains(dto.getEmpId())) {
				employees.add(dto.getEmpId());
			}
		}
		Collections.sort(employees); //slows performance, but is used for easier visual testing
		return employees;
	}

	public static List<Integer> listAllProjects(List<? extends Serializable> resources) {
		//crate lists with unique values. Use List instead of Set, because I want to get values by index
		List<Integer> projects = new ArrayList<>();
		for (Serializable resource : resources) {
			LineDTO dto = (LineDTO) resource;
			if (!projects.contains(dto.getProjectId())) {
				projects.add(dto.getProjectId());
			}
		}
		Collections.sort(projects); //slows performance, but is used for easier visual testing
		return projects;
	}

	public static HashMap<Integer, Set<Integer>> listProjectsByPairs(List<? extends Serializable> resources, List<? extends Serializable> projects) {
		//loops through all items and put in list with unique EmpIDs
		HashMap<Integer, Set<Integer>> projectsWithPairs = new HashMap<>();
		int projectIdMatcher = 1;
		for (
				int i = 0; i < projects.size(); i++) {
			Set<Integer> employeesPerProject = new HashSet<>();
			//
			for (Serializable resource : resources) {
				LineDTO dto = (LineDTO) resource;
				if (dto.getProjectId() == projectIdMatcher) {
					employeesPerProject.add(dto.getEmpId());
				}
			}
			System.out.printf("ProjID:%d, EmpID: %s %n", projectIdMatcher, employeesPerProject);
			projectsWithPairs.put(projectIdMatcher, employeesPerProject);
			projectIdMatcher++;
		}
		return projectsWithPairs;
	}
}
