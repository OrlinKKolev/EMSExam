package com.academy.EMSExam;

import com.academy.EMSExam.dto.LineDTO;
import com.academy.EMSExam.model.Pair;
import com.academy.EMSExam.utils.Globals;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		var resources = Globals.resources;
		var employees = listAllEmployees(resources);
		var projects = listAllProjects(resources);
		var projectsByParis = listProjectsByPairs(resources, projects);

//TEST	System.out.println(resources);
//TEST	System.out.println(employees);
//TEST	System.out.println(projects);
		System.out.println(projectsByParis);

	//pair creation
		var pairs = pairs(projects, projectsByParis);
//TEST		System.out.println(Arrays.toString(pairs.toArray()));
//TEST		System.out.println(pairs.getFirst().duration());

	//durations calculation
		var durations = calculateDurations(pairs);
		System.out.println(durations);
		System.out.println(Globals.bestTeam);
		System.out.println(Globals.bestTeamDuration);
	}

	public static List<Pair> pairs(List<Integer> projects, HashMap<Integer, List<Integer>> projectsByParis) {
		List<Pair> pairs = new ArrayList<>();
		for (int projId : projects) {
			var empList = projectsByParis.get(projId);
//TEST			System.out.printf("%n<-->empList: %s%n", empList.toString());
			if (empList.size() == 2) {
				Pair pair = pairMaker(projId, empList);
//TEST			System.out.printf("key: %s%n", pair.key());
//TEST			System.out.printf("projId: %d%n", pair.getProjectId());
//TEST			System.out.printf("pairId: %d%n", pair.getPairid());
//TEST			System.out.printf("duration: %d%n", pair.duration());
				pairs.add(pair);
			} else {
				System.out.println("Big Code");
			}
		}

		return pairs;
	}


	public static Map<String, Long> calculateDurations(List<Pair> pairs) {
		//iterate through the list of pairs and calculate total duration of the pair. Put in new HashMap <key,duration>
		Map<String, Long> durations = new HashMap<>();
		for (Pair pairKey : pairs) {
			long sumDuration = 0;
			for (Pair pair : pairs) {
				if (pair.key().equals(pairKey.key())) {
					sumDuration += pair.duration();
				}
				durations.put(pairKey.key(), sumDuration);
			}
			if (Globals.bestTeamDuration < sumDuration) {
				Globals.bestTeamDuration = sumDuration;
				Globals.bestTeam = pairKey.key();
			}
		}

		return durations;
	}

	public static Pair pairMaker(int projId, List<Integer> empList) {
		int emp1 = empList.get(0);
		int emp2 = empList.get(1);
		Pair pair = new Pair(Globals.pairCounter, projId, emp1, emp2);
		pair.setStartDate1(extractDateFrom(emp1, projId, Globals.resources));
		pair.setStartDate2(extractDateFrom(emp2, projId, Globals.resources));
		pair.setEndDate1(extractDateTo(emp1, projId, Globals.resources));
		pair.setEndDate2(extractDateTo(emp2, projId, Globals.resources));
				return pair;
	}

	public static LocalDate extractDateFrom(int empId, int projId, List<? extends Serializable> resources) {
		for (Serializable resource : resources) {
			LineDTO dto = (LineDTO) resource;
			if (dto.getEmpId() == empId && dto.getProjectId() == projId) {
				return dto.getDateFrom();
			}
		}
		return null;
	}

	public static LocalDate extractDateTo(int empId, int projId, List<? extends Serializable> resources) {
		for (Serializable resource : resources) {
			LineDTO dto = (LineDTO) resource;
			if (dto.getEmpId() == empId && dto.getProjectId() == projId) {
				return dto.getDateTo();
			}
		}
		return null;
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

	public static HashMap<Integer, List<Integer>> listProjectsByPairs(List<? extends Serializable> resources, List<? extends Serializable> projects) {
		//loops through all items and put in list with unique EmpIDs
		HashMap<Integer, List<Integer>> projectsWithPairs = new HashMap<>();
		int projectIdMatcher = 1;
		for (int i = 0; i < projects.size(); i++) {
			List<Integer> employeesPerProject = new ArrayList<>();
			for (Serializable resource : resources) {
				LineDTO dto = (LineDTO) resource;
				if (!employeesPerProject.contains(dto.getEmpId())) {
					if (dto.getProjectId() == projectIdMatcher) {
						employeesPerProject.add(dto.getEmpId());
					}
				}
			}
			Collections.sort(employeesPerProject);
//TEST		System.out.printf("ProjID:%d, EmpID: %s %n", projectIdMatcher, employeesPerProject);
			projectsWithPairs.put(projectIdMatcher, employeesPerProject);
			projectIdMatcher++;
		}
		return projectsWithPairs;
	}
}
