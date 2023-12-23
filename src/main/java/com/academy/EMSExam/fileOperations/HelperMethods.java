package com.academy.EMSExam.fileOperations;

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

}
