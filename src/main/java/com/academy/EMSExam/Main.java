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
		List<Integer> projects = listAllProjects(resources);
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		queue.addAll(projects);
		var projectsByParis = listProjectsByPairs(resources, queue);

//TEST	System.out.println(resources);
//TEST	System.out.println(employees);
		System.out.println(projects);
		System.out.println(projectsByParis);

		//pair creation
		var pairs = pairs(projects, projectsByParis);

		//durations calculation
		var durations = calculateDurations(pairs);
		System.out.println(durations);
		System.out.println(Globals.bestTeam);
		System.out.println(Globals.bestTeamDuration);
		System.out.println(Globals.bestTeamProjects);

	}

	/**
	 * iterate through the list of pairs and calculate total duration of the pair. Put in new HashMap <key,duration>
	 */

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

	public static List<Pair> pairs(List<Integer> projects, HashMap<Integer, List<Integer>> projectsByParis) {
		List<Pair> pairs = new ArrayList<>();
		for (int projId : projects) {
			var empList = projectsByParis.get(projId);
			if (empList.size() >1 ) {
				Pair pair = pairMaker(projId, empList);
				pairs.add(pair);
			} else {
				System.out.println("Big Code");
			}
		}
		return pairs;
	}

	/**
	 * iterate through the list of pairs and calculate total duration of the pair. Put in new HashMap <key,duration>
	 */
	public static Map<String, Long> calculateDurations(List<Pair> pairs) {
		Map<String, Long> durations = new HashMap<>();
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
	public static HashMap<Integer, List<Integer>> listProjectsByPairs(List<? extends Serializable> resources, ArrayDeque<Integer> projectsQueue) {
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
//TEST		System.out.printf("ProjID:%d, EmpID: %s %n", projectIdMatcher, employeesPerProject);
				projectsWithPairs.put(projectIdMatcher, employeesPerProject);
//				projectsQueue.removeFirst();
			}
		}
		return projectsWithPairs;
	}

}
