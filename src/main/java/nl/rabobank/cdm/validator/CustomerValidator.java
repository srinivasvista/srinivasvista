package nl.rabobank.cdm.validator;

import static nl.rabobank.cdm.constants.CDMConstants.OPERATION_UPDATE;
import static nl.rabobank.cdm.constants.CDMConstants.PATTERN_CUSTOMER_ADDRESS;
import static nl.rabobank.cdm.constants.CDMConstants.PATTERN_CUSTOMER_ID;
import static nl.rabobank.cdm.constants.CDMConstants.PATTERN_CUSTOMER_NAME;
import static nl.rabobank.cdm.util.CustomerUtil.isNotValidPattern;
import static nl.rabobank.cdm.util.CustomerUtil.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import nl.rabobank.cdm.exception.CDMValidationException;
import nl.rabobank.cdm.model.CustomerDetails;

@Component
@Slf4j
public class CustomerValidator {
	
	
	public void validateCustomerDetails(CustomerDetails customerDetails, String operation) throws CDMValidationException{
		String errorMessage = null;
		List<String> errorMessageList = new ArrayList<>();
		
		if (OPERATION_UPDATE.equals(operation)) {
			validateField("id", customerDetails.getId(), PATTERN_CUSTOMER_ID, errorMessageList);
		}
		validateField("firstName", customerDetails.getFirstName(), PATTERN_CUSTOMER_NAME, errorMessageList);
		validateField("lastName", customerDetails.getLastName(), PATTERN_CUSTOMER_NAME, errorMessageList);
		validateField("address", customerDetails.getAddress(), PATTERN_CUSTOMER_ADDRESS, errorMessageList);
		validateDateField("dob", customerDetails.getDob(), errorMessageList);
		
		if (errorMessageList.size() > 0) {
			errorMessage= String.join(", ", errorMessageList);
			throw new CDMValidationException(errorMessage);
		}
	}


	private void validateField(String fieldName, String fieldValue, Pattern pattern, List<String> errorMessageList) {
		if (isNullOrEmpty(fieldValue)
				|| isNotValidPattern(fieldValue, pattern)){
			errorMessageList.add(fieldName +" is invalid");
		}
	}


	private void validateDateField(String fieldName, String fieldvalue, List<String> errorMessageList) {
		if (isNullOrEmpty(fieldvalue) || stringToDate(fieldvalue) == null ) {
			errorMessageList.add(fieldName +" is invalid");
		}
		
	}
}
