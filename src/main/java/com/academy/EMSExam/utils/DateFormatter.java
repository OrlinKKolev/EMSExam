package com.academy.EMSExam.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class DateFormatter {
	public static LocalDate parseDate(String date) {
		DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder()
				.append(DateTimeFormatter
						.ofPattern("[yyyyMMdd]" +
								"[yyyy-MM-dd]" +
								"[yyyy.MM.dd]" +
								"[MM/dd/yyyy]" +
								"[dd-MM-yyyy]" +
								"[dd.MM.yyyy]"));
		DateTimeFormatter dateTimeFormatter = dateTimeFormatterBuilder.toFormatter();
		return LocalDate.parse(date, dateTimeFormatter);
	}
}
