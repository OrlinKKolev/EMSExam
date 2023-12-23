package com.academy.EMSExam.utils;

import java.util.ArrayList;
import java.util.List;

public class HelperMethods {

	public static boolean hasHeaders = false;
	public static String headers;

	public static boolean isInt(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			int isInt = Integer.parseInt(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}



	public static String nullStringConverter(String text) {
//		List<String> nulls = new ArrayList<>();
//		nulls.add(" ");
//		nulls.add("null");
//		nulls.add("Null");
//		nulls.add("nUll");
//		nulls.add("nuLl");
//		nulls.add("nulL");
//text.
//		nulls.add("NULL");

		if (text.equalsIgnoreCase("null") || text.equals(" ")) {
			return null;
		} else {
			return text;
		}
	}

}
