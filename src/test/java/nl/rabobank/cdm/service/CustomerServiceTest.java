package nl.rabobank.cdm.service;

import static nl.rabobank.cdm.constants.CDMConstants.*;
import static nl.rabobank.cdm.constants.CDMConstants.STATUS_SUCCESS;
import static nl.rabobank.cdm.util.CustomerUtil.stringToDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import nl.rabobank.cdm.data.entities.Customer;
import nl.rabobank.cdm.data.repository.CustomerRepository;
import nl.rabobank.cdm.exception.CDMApiException;
import nl.rabobank.cdm.exception.CDMValidationException;
import nl.rabobank.cdm.model.CustomerDetails;
import nl.rabobank.cdm.validator.CustomerValidator;


public class CustomerServiceTest {
	
	@InjectMocks
	CustomerServiceImpl customerService;
	
	@Mock
	CustomerValidator customerValidator;
	
	@Mock
	CustomerRepository customerRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test_get_all_customers_happy() throws CDMApiException {
		List<Customer> expectedCustomerList = new ArrayList<>();
		expectedCustomerList.add(new Customer("123e4567-e89b-12d3-a456-426614174000", "testuser1", "lastname1", "address1", new Date()));
		expectedCustomerList.add(new Customer("123e4567-e89b-12d3-a456-426614174001", "testuser2", "lastname2", "address2", new Date()));
		when(customerRepository.findAll()).thenReturn(expectedCustomerList);
		List<Customer> actualCustomerList = customerService.getCustomers();
		assertEquals(expectedCustomerList, actualCustomerList);
		
	}
	
	@Test
	public void test_get_all_customers_empty() throws CDMApiException {
		when(customerRepository.findAll()).thenReturn(new ArrayList<Customer>());
		CDMApiException cdmApiException = null;
		try {
			customerService.getCustomers();
		}
		catch(CDMApiException e) {
			cdmApiException = e;
		}
		assertEquals(NO_RECORD_FOUND, cdmApiException.getMessage());
		
	}
	
	@Test
	public void test_get_customer_by_id_happy() throws CDMApiException {
		Customer expectedCustomer= new Customer("123e4567-e89b-12d3-a456-426614174000", "testuser1", "lastname1", "address1", stringToDate("01-01-2000")); 
		Optional<Customer> expectedCustomerOpt = Optional.of(expectedCustomer);
		when(customerRepository.findById("123e4567-e89b-12d3-a456-426614174000")).thenReturn(expectedCustomerOpt);
		Customer actualCustomer = customerService.getCustomerById("123e4567-e89b-12d3-a456-426614174000");
		assertEquals(expectedCustomer, actualCustomer);
		
	}
	
	@Test
	public void test_get_customer_by_id_empty() throws CDMApiException {
		Optional<Customer> expectedCustomer = Optional.empty();
		CDMApiException cdmApiException = null;
		when(customerRepository.findById("123e4567-e89b-12d3-a456-426614174000")).thenReturn(expectedCustomer);
		try {
			customerService.getCustomerById("123e4567-e89b-12d3-a456-426614174000");
		}
		catch(CDMApiException e) {
			cdmApiException = e;
		}
		assertEquals(NO_RECORD_FOUND, cdmApiException.getMessage());
		
	}
	
	@Test
	public void test_get_customer_by_fieldName_happy() throws CDMApiException {
		List<Customer> expectedCustomerList = new ArrayList<>();
		expectedCustomerList.add(new Customer("123e4567-e89b-12d3-a456-426614174000", "testuser1", "lastname1", "address1", new Date()));
		when(customerRepository.findByFirstNameContainsAndLastNameContains("testuser1", "lastname1")).thenReturn(expectedCustomerList);
		List<Customer> actualCustomerList = customerService.getCustomerByFieldName("testuser1", "lastname1");
		assertEquals(expectedCustomerList, actualCustomerList);
		
	}
	
	@Test
	public void test_get_customer_by_fieldName_by_first_name() throws CDMApiException {
		List<Customer> expectedCustomerList = new ArrayList<>();
		expectedCustomerList.add(new Customer("123e4567-e89b-12d3-a456-426614174000", "testuser1", "lastname1", "address1", new Date()));
		when(customerRepository.findByFirstNameContains("testuser1")).thenReturn(expectedCustomerList);
		List<Customer> actualCustomerList = customerService.getCustomerByFieldName("testuser1", null);
		assertEquals(expectedCustomerList, actualCustomerList);
		
	}
	
	@Test
	public void test_get_customer_by_fieldName_by_last_name() throws CDMApiException {
		List<Customer> expectedCustomerList = new ArrayList<>();
		expectedCustomerList.add(new Customer("123e4567-e89b-12d3-a456-426614174000", "testuser1", "lastname1", "address1", new Date()));
		when(customerRepository.findByLastNameContains("lastname1")).thenReturn(expectedCustomerList);
		List<Customer> actualCustomerList = customerService.getCustomerByFieldName(null, "lastname1");
		assertEquals(expectedCustomerList, actualCustomerList);
		
	}
	
	@Test
	public void test_get_customer_by_fieldName_empty() throws CDMApiException {
		when(customerRepository.findAll()).thenReturn(new ArrayList<Customer>());
		CDMApiException cdmApiException = null;
		try {
			customerService.getCustomerByFieldName("testuser1", "lastname1");
		}
		catch(CDMApiException e) {
			cdmApiException = e;
		}
		assertEquals(NO_RECORD_FOUND, cdmApiException.getMessage());
		
	}
	
	@Test
	public void test_save_valid_customer() throws CDMApiException {
		Customer customer = new Customer("123e4567-e89b-12d3-a456-426614174000", "testuser1", "lastname1", "address1", new Date());
		when(customerRepository.save(customer)).thenReturn(customer);
		CDMApiException cdmApiException = null;
		try {
			customerService.saveCustomer(new CustomerDetails("1", "testuser1", "lastname1", "address1", "01-01-2000"));
		}
		catch(CDMApiException ex) {
			cdmApiException = ex;
		}
		
		assertNull(cdmApiException);
	}
	
	@Test
	public void test_save_invalid_customer() throws CDMApiException {
		CDMValidationException cdmValidationException = null;
		try {
			Mockito.doThrow(new CDMValidationException("id is invalid")).when(customerValidator).validateCustomerDetails(any(), any());
			customerService.saveCustomer(new CustomerDetails("1", "testuser1", "lastname1", "address1", "01-01-2000"));
		}
		catch(CDMValidationException ex) {
			cdmValidationException = ex;
		}
		
		assertNotNull(cdmValidationException);
		assertEquals("id is invalid", cdmValidationException.getMessage());
	}
	
	@Test
	public void test_save_customer_failed() throws CDMApiException {
		when(customerRepository.save(any())).thenThrow(new IllegalArgumentException("invalid"));
		CDMApiException cdmApiException = null;
		try {
			customerService.saveCustomer(new CustomerDetails("123e4567-e89b-12d3-a456-426614174000", "testuser1", "lastname1", "address1", "01-01-2000"));
		}
		catch(CDMApiException ex) {
			cdmApiException = ex;
		}
		
		assertNotNull(cdmApiException);
		assertEquals(CUSTOMER_SAVE_OPERATION_FAILED, cdmApiException.getMessage());
	}

	@Test
	public void test_update_valid_customer() throws CDMApiException {
		Customer customer = new Customer("123e4567-e89b-12d3-a456-426614174000", "testuser1", "lastname1", "address1", new Date());
		Optional<Customer> expectedCustomerOpt = Optional.of(customer);
		when(customerRepository.findById("123e4567-e89b-12d3-a456-426614174000")).thenReturn(expectedCustomerOpt);
		when(customerRepository.save(customer)).thenReturn(customer);
		CDMApiException cdmApiException = null;
		try {
			customerService.updateCustomer(new CustomerDetails("123e4567-e89b-12d3-a456-426614174000", "testuser1", "lastname1", "address1", "01-01-2000"));
		}
		catch(CDMApiException ex) {
			cdmApiException = ex;
		}
		
		assertNull(cdmApiException);
	}
	
	@Test
	public void test_update_invalid_customer() throws CDMApiException {
		CDMValidationException cdmValidationException = null;
		try {
			Mockito.doThrow(new CDMValidationException("id is invalid")).when(customerValidator).validateCustomerDetails(any(), any());
			customerService.updateCustomer(new CustomerDetails("123e4567-e89b-12d3-a456-42661417400", "testuser1", "lastname1", "address1", "01-01-2000"));
		}
		catch(CDMValidationException ex) {
			cdmValidationException = ex;
		}
		
		assertNotNull(cdmValidationException);
		assertEquals("id is invalid", cdmValidationException.getMessage());
	}
	
	@Test
	public void test_update_customer_not_found() throws CDMApiException {
		Optional<Customer> expectedCustomerOpt = Optional.empty();
		when(customerRepository.findById("123e4567-e89b-12d3-a456-426614174000")).thenReturn(expectedCustomerOpt);
		CDMApiException cdmApiException = null;
		try {
			customerService.updateCustomer(new CustomerDetails("123e4567-e89b-12d3-a456-426614174000", "testuser1", "lastname1", "address1", "01-01-2000"));
		}
		catch(CDMApiException ex) {
			cdmApiException = ex;
		}
		
		assertNotNull(cdmApiException);
		assertEquals(NO_RECORD_FOUND, cdmApiException.getMessage());
	}
	
	@Test
	public void test_update_customer_failed() throws CDMApiException {
		Customer expectedCustomer= new Customer("123e4567-e89b-12d3-a456-426614174000", "testuser1", "lastname1", "address1", stringToDate("01-01-2000"));
		Optional<Customer> expectedCustomerOpt = Optional.of(expectedCustomer);
		when(customerRepository.findById(any())).thenReturn(expectedCustomerOpt);
		when(customerRepository.save(any())).thenThrow(new IllegalArgumentException("invalid"));
		CDMApiException cdmApiException = null;
		try {
			customerService.updateCustomer(new CustomerDetails("123e4567-e89b-12d3-a456-426614174000", "testuser1", "lastname1", "address1", "01-01-2000"));
		}
		catch(CDMApiException ex) {
			cdmApiException = ex;
		}
		
		assertNotNull(cdmApiException);
		assertEquals(CUSTOMER_UPDATE_OPERATION_FAILED, cdmApiException.getMessage());
	}
	
	
}
