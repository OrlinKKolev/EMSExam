package com.academy.EMSExam.utils;

import com.academy.EMSExam.repository.CSVRepository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public final class Globals {

	public static final List<? extends Serializable> resources = CSVRepository.read("./src/main/resources/files/generated_data.csv");
	public static String bestTeam;
	public static long bestTeamDuration;
	public static int pairCounter = 1;
	public static HashMap<Integer, Long> bestTeamProjects = new HashMap<>();

	private Globals() {
	}
}
