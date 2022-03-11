package nl.rabobank.cdm.util;

import static nl.rabobank.cdm.constants.CDMConstants.dateFormatter;
import static nl.rabobank.cdm.util.CustomerUtil.dateToString;
import static nl.rabobank.cdm.util.CustomerUtil.stringToDate;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import nl.rabobank.cdm.data.entities.Customer;
import nl.rabobank.cdm.model.CustomerDetails;

public class CustomerUtil {
	
	public static boolean isNotValidPattern(String fieldvalue, Pattern regexPattern) {
		Matcher matcher = regexPattern.matcher(fieldvalue);
		if (!matcher.matches()) {
			return true;
		}
		return false;
	}
	
	public static boolean isNullOrEmpty(String fieldvalue) {
		if (fieldvalue == null || "".equals(fieldvalue)) {
			return true;
		}
		return false;
	}
	
	public static Date stringToDate(String fieldvalue) {
		Date convertedDate = null;
		try {
			dateFormatter.setLenient(false);
			convertedDate = dateFormatter.parse(fieldvalue);
		} catch (ParseException e) {
			//log error
		}
		return convertedDate;
	}
	
	public static String dateToString(Date date) {
		String dateToString = null;
		try {
			dateToString = dateFormatter.format(date);
		} catch (Exception e) {
			//log error
		}
		return dateToString;
	}


	public static Integer calculateAge(Date dob) {
		Integer age = null;
		if (dob != null) {
			LocalDate dobDate = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
			LocalDate currDate = LocalDate.now();  
			age = Period.between(dobDate, currDate).getYears();  
		}
		return age;
	}
	
}
