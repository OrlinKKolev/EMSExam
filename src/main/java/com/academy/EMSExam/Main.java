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
		List<Integer> employees = listAllEmployees(resources);
		List<Integer> projects = listAllProjects(resources);
		ArrayDeque<Integer> empIdQueue = new ArrayDeque<>();
		empIdQueue.addAll(projects);
		HashMap<Integer, List<Integer>> projectsByEmpId = listProjectsByEmpId(resources, empIdQueue);
		List<Pair> pairs = pairs(projects, projectsByEmpId);
		HashMap<String, Long> durations = calculateDurations(pairs);
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Best Team Identification System");
		boolean isRunning = true;
		while (isRunning) {
			displayCommands();
			int command = Integer.parseInt(sc.nextLine());
			switch (command) {
				case 1:
					System.out.printf("Best Team: %s%n", Globals.bestTeam);
					System.out.printf("Total days on common projects: %d%n", Globals.bestTeamDuration);
					System.out.printf("Days distributed by projects %s%n", Globals.bestTeamProjects);
					break;
				case 2:
					System.out.println(durations);
					break;
				case 3:
					System.out.println(employees);
					break;
				case 4:
					System.out.println(projects);
					break;
				case 9:
					System.out.println(resources);
					break;
				case 0:
					return;
			}

		}
	}

	private static void displayCommands() {
		System.out.println("Choose an option from the list below:");
		System.out.println("1. Calculate and display best team");
		System.out.println("2. List durations by pairs");
		System.out.println("3. List all employees");
		System.out.println("4. List all projects");
		System.out.println("9. List resource data");
		System.out.println("0. Abort");
	}

	/**
	 * creates list of all unique pairs of employees, working on common projects.
	 */
	public static List<Pair> pairs(List<Integer> projects, HashMap<Integer, List<Integer>> projectsByParis) {
		List<Pair> pairs = new ArrayList<>();
		for (int projId : projects) {
			var empList = projectsByParis.get(projId);
			for (int i = 0; i < empList.size(); i++) {
				for (int j = i + 1; j < empList.size(); j++) {
					Pair pair = pairMaker(projId, empList, i, j);
					pairs.add(pair);
				}
			}
		}
		return pairs;
	}

	/**
	 * iterate through the list of pairs and calculate total duration of the pair. Put in new HashMap <key,duration>
	 */
	public static Pair pairMaker(int projId, List<Integer> empList, int i, int j) {
		int emp1 = empList.get(i);
		int emp2 = empList.get(j);
		Pair pair = new Pair(Globals.pairCounter, projId, emp1, emp2);
		pair.setStartDate1(extractDateFrom(emp1, projId, Globals.resources));
		pair.setStartDate2(extractDateFrom(emp2, projId, Globals.resources));
		pair.setEndDate1(extractDateTo(emp1, projId, Globals.resources));
		pair.setEndDate2(extractDateTo(emp2, projId, Globals.resources));
		return pair;
	}

	/**
	 * iterate through the list of pairs and calculate total duration of the pair. Put in new HashMap <key,duration>
	 */
	public static HashMap<String, Long> calculateDurations(List<Pair> pairs) {
		HashMap<String, Long> durations = new HashMap<>();
		for (Pair pairKey : pairs) {
			long sumDuration = 0;
			for (Pair pair : pairs) {
				if (pair.key().equals(pairKey.key())) {
					sumDuration += pair.calcDuration();
					durations.put(pairKey.key(), sumDuration);
				}
			}
			if (Globals.bestTeamDuration < sumDuration) {
				Globals.bestTeamDuration = sumDuration;
				Globals.bestTeam = pairKey.key();
			}
		}
		for (Pair pair : pairs) {
			if (pair.key().equals(Globals.bestTeam)) {
				Globals.bestTeamProjects.put(pair.getProjectId(), pair.calcDuration());
			}
		}
		return durations;
	}

	/**
	 * extracts dateFrom of the resource list.
	 */
	public static LocalDate extractDateFrom(int empId, int projId, List<? extends Serializable> resources) {
		for (Serializable resource : resources) {
			LineDTO dto = (LineDTO) resource;
			if (dto.getEmpId() == empId && dto.getProjectId() == projId) {
				return dto.getDateFrom();
			}
		}
		return null;
	}

	/**
	 * extracts dateTo of the resource list.
	 */
	public static LocalDate extractDateTo(int empId, int projId, List<? extends Serializable> resources) {
		for (Serializable resource : resources) {
			LineDTO dto = (LineDTO) resource;
			if (dto.getEmpId() == empId && dto.getProjectId() == projId) {
				return dto.getDateTo();
			}
		}
		return null;
	}

	/**
	 * create lists with unique values. Use List instead of Set, because I want to get values by index
	 */
	public static List<Integer> listAllEmployees(List<? extends Serializable> resources) {
		List<Integer> employees = new ArrayList<>();
		for (Serializable resource : resources) {
			LineDTO dto = (LineDTO) resource;
			if (!employees.contains(dto.getEmpId())) {
				employees.add(dto.getEmpId());
			}
		}
		return employees;
	}

	/**
	 * crate lists with unique values. Use List instead of Set, because I want to get values by index
	 */
	public static List<Integer> listAllProjects(List<? extends Serializable> resources) {
		List<Integer> projects = new ArrayList<>();
		for (Serializable resource : resources) {
			LineDTO dto = (LineDTO) resource;
			if (!projects.contains(dto.getProjectId())) {
				projects.add(dto.getProjectId());
			}
		}
		return projects;
	}

	/**
	 * loops through all items and put in list with unique EmpIDs
	 */
	public static HashMap<Integer, List<Integer>> listProjectsByEmpId(List<? extends Serializable> resources, ArrayDeque<Integer> projectsQueue) {
		HashMap<Integer, List<Integer>> projectsWithPairs = new HashMap<>();
		while (!projectsQueue.isEmpty()) {
			int size = projectsQueue.size();
			for (int i = 0; i < size; i++) {
				int projectIdMatcher = projectsQueue.pop();
				List<Integer> employeesPerProject = new ArrayList<>();
				for (Serializable resource : resources) {
					LineDTO dto = (LineDTO) resource;
					if (!employeesPerProject.contains(dto.getEmpId())) {
						if (dto.getProjectId() == projectIdMatcher) {
							employeesPerProject.add(dto.getEmpId());
						}
					}
				}
				projectsWithPairs.put(projectIdMatcher, employeesPerProject);
			}
		}
		return projectsWithPairs;
	}
}
