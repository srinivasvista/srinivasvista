package nl.rabobank.cdm.constants;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class CDMConstants {
	
	public static final Pattern PATTERN_CUSTOMER_NAME = Pattern.compile("[a-zA-Z-]+");
	public static final Pattern PATTERN_CUSTOMER_ID = Pattern.compile("[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}");
	public static final Pattern PATTERN_CUSTOMER_ADDRESS = Pattern.compile("[a-zA-Z0-9-, ]+");
	public static final Pattern PATTERN_CUSTOMER_DOB = Pattern.compile("[0-9-]+");
	public static final String DATE_FORMAT = "dd-MM-yyyy";
	public static final SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
	public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

	public static final String NO_RECORD_FOUND = "No record found";
	public static final String CUSTOMER_RECORD_NOT_FOUND = "Customer Record not found";
	public static final String CUSTOMER_UPDATE_OPERATION_FAILED = "Customer Update operation failed";
	public static final String CUSTOMER_SAVE_OPERATION_FAILED = "Customer Save operation failed";
	
	public static final String STATUS_SUCCESS = "SUCCESS";
	public static final String STATUS_FAILURE = "FAILURE";
	public static final String UPDATE_OPERATION_SUCCESSFUL = "Update Operation successful";
	public static final String SAVE_OPERATION_SUCCESSFUL = "Save Operation successful";
	
	public static final String OPERATION_UPDATE = "update";
	public static final String OPERATION_SAVE = "save";

	
}
