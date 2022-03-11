package nl.rabobank.cdm.validator;

import static nl.rabobank.cdm.constants.CDMConstants.OPERATION_SAVE;
import static nl.rabobank.cdm.constants.CDMConstants.OPERATION_UPDATE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import nl.rabobank.cdm.exception.CDMValidationException;
import nl.rabobank.cdm.model.CustomerDetails;

public class CustomerValidatorTest {
	
	@InjectMocks
	CustomerValidator customerValidator;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test_valid_customer_details() {
		CustomerDetails customerDetails = new CustomerDetails("123e4567-e89b-12d3-a456-426614174000", "Srini", "Venkat", "address1", "01-01-2000", "22");
		CDMValidationException cdmValidationException = null;
		try {
			customerValidator.validateCustomerDetails(customerDetails, OPERATION_SAVE);
		}
		catch(CDMValidationException ex) {
			cdmValidationException = ex;
		}
		assertNull(cdmValidationException);
		
	}

	@Test
	public void test_invalid_id() {
		CustomerDetails customerDetails = new CustomerDetails("1#", "testuser", "lastname", "address1", "01-01-2000", "22");
		CDMValidationException cdmValidationException = null;
		try {
			customerValidator.validateCustomerDetails(customerDetails, OPERATION_UPDATE);
		}
		catch(CDMValidationException ex) {
			cdmValidationException = ex;
		}
		assertNotNull(cdmValidationException);
		
	}
	
	@Test
	public void test_invalid_values() {
		CustomerDetails customerDetails = new CustomerDetails("", "testuser#", "lastname#", "address()1", "01-01-2000", "22");
		CDMValidationException cdmValidationException = null;
		try {
			customerValidator.validateCustomerDetails(customerDetails, OPERATION_SAVE);
		}
		catch(CDMValidationException ex) {
			cdmValidationException = ex;
		}
		System.out.println(cdmValidationException.getMessage());
		assertNotNull(cdmValidationException);
		
	}
	
	@Test
	public void test_invalid_date() {
		CustomerDetails customerDetails = new CustomerDetails("123e4567-e89b-12d3-a456-426614174000", "testuser", "lastname", "address1", "30-02-2000", "21");
		CDMValidationException cdmValidationException = null;
		try {
			customerValidator.validateCustomerDetails(customerDetails, OPERATION_SAVE);
		}
		catch(CDMValidationException ex) {
			cdmValidationException = ex;
		}
		System.out.println(cdmValidationException.getMessage());
		assertNotNull(cdmValidationException);
		
	}
	
	
}
