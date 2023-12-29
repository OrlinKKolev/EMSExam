package com.academy.EMSExam.utils;

import com.academy.EMSExam.repository.CSVRepository;

import java.io.Serializable;
import java.util.List;

public class Globals {
	public static String bestTeam;
	public static long bestTeamDuration;
	public static int pairCounter =1;
	public static List<? extends Serializable> resources = CSVRepository.read("./src/main/resources/files/generated_data.csv");
}
