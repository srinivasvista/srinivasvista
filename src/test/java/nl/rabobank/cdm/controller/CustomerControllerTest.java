package nl.rabobank.cdm.controller;

import static nl.rabobank.cdm.util.CustomerPojoUtil.getCustomerEntity;
import static nl.rabobank.cdm.util.CustomerUtil.stringToDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import nl.rabobank.cdm.controllers.CustomerController;
import nl.rabobank.cdm.data.entities.Customer;
import nl.rabobank.cdm.exception.CDMApiException;
import nl.rabobank.cdm.model.CustomerDetails;
import nl.rabobank.cdm.service.CustomerService;


public class CustomerControllerTest {
	
	@InjectMocks
	CustomerController customerController;
	
	@Mock
	CustomerService customerService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test_get_all_customers() throws CDMApiException {
		List<Customer> customerList = new ArrayList<>();
		customerList.add(new Customer("123e4567-e89b-12d3-a456-426614174000", "testuser1", "lastname1", "address1", new Date()));
		customerList.add(new Customer("123e4567-e89b-12d3-a456-426614174001", "testuser2", "lastname2", "address2", new Date()));
		when(customerService.getCustomers()).thenReturn(customerList);
		List<CustomerDetails> actualCustomerList = customerController.getCustomers();
		assertEquals(actualCustomerList.size(), 2);
	}
	
	@Test
	public void test_get_customer_by_id() throws CDMApiException {
		Customer expectedCustomer= new Customer("123e4567-e89b-12d3-a456-426614174000", "testuser1", "lastname1", "address1", stringToDate("01-01-2000"));
		when(customerService.getCustomerById("123e4567-e89b-12d3-a456-426614174000")).thenReturn(expectedCustomer);
		CustomerDetails actualCustomer = customerController.getCustomerById("123e4567-e89b-12d3-a456-426614174000");
		assertNotNull(getCustomerEntity(actualCustomer));
		
	}
	
	@Test
	public void test_get_customer_by_name() throws CDMApiException {
		List<Customer> customerList = new ArrayList<>();
		customerList.add(new Customer("123e4567-e89b-12d3-a456-426614174000", "testuser1", "lastname1", "address1", stringToDate("01-01-2000")));
		customerList.add(new Customer("123e4567-e89b-12d3-a456-426614174001", "testuser2", "lastname2", "address2", stringToDate("02-02-2000")));
		when(customerService.getCustomerByFieldName("testUser1", "lastName1")).thenReturn(customerList);
		List<CustomerDetails> actualCustomerList = customerController.getCustomerByFieldName("testUser1", "lastName1");
		assertEquals(actualCustomerList.size(), 2);
		
	}
	
	@Test
	public void test_save_customer() throws CDMApiException {
		Customer customer = new Customer("123e4567-e89b-12d3-a456-426614174000", "testuser1", "lastname1", "address1", stringToDate("01-01-2000"));
		when(customerService.saveCustomer(any())).thenReturn(customer);
		CustomerDetails customerDetails = customerController.saveCustomer(new CustomerDetails("123e4567-e89b-12d3-a456-426614174000", "testuser1", "lastname1", "address1", "01-01-2000"));
		assertNotNull(customerDetails);
		
	}
	
	@Test
	public void test_update_customer() throws CDMApiException {
		Customer customer = new Customer("123e4567-e89b-12d3-a456-426614174000", "testuser1", "lastname1", "address1", stringToDate("01-01-2000"));
		when(customerService.updateCustomer(any())).thenReturn(customer);
		CustomerDetails customerDetails = customerController.updateCustomer(new CustomerDetails("1", "testuser1", "lastname1", "address1", "01-01-2000"));
		assertNotNull(customerDetails);		
	}

	
	
}
