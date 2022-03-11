package nl.rabobank.cdm.service;

import static nl.rabobank.cdm.constants.CDMConstants.CUSTOMER_SAVE_OPERATION_FAILED;
import static nl.rabobank.cdm.constants.CDMConstants.CUSTOMER_UPDATE_OPERATION_FAILED;
import static nl.rabobank.cdm.constants.CDMConstants.NO_RECORD_FOUND;
import static nl.rabobank.cdm.constants.CDMConstants.OPERATION_SAVE;
import static nl.rabobank.cdm.constants.CDMConstants.OPERATION_UPDATE;
import static nl.rabobank.cdm.util.CustomerPojoUtil.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import nl.rabobank.cdm.data.entities.Customer;
import nl.rabobank.cdm.data.repository.CustomerRepository;
import nl.rabobank.cdm.exception.CDMApiException;
import nl.rabobank.cdm.model.CustomerDetails;
import nl.rabobank.cdm.validator.CustomerValidator;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerValidator customerValidator;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	
	public List<Customer> getCustomers() throws CDMApiException {
		List<Customer> customerList = new ArrayList<>();
		Iterable<Customer> customerIterator = customerRepository.findAll();
		customerIterator.forEach(customerList::add);
		
		if (customerList.size() == 0) {
			throw new CDMApiException(NO_RECORD_FOUND);
		}
        return customerList;
    }

	
	
	 public Customer getCustomerById(String id) throws CDMApiException {
		 Optional<Customer> customer = customerRepository.findById(id);
	     if (customer.isEmpty()) {
	    	 throw new CDMApiException(NO_RECORD_FOUND);
	     }
	     return customer.get();
	 }
	 
	 
	 
    public List<Customer> getCustomerByFieldName(String firstName, String lastName) throws CDMApiException {
    	List<Customer> customerList = null;
    	if (firstName == null) {
    		customerList = customerRepository.findByLastNameContains(lastName);
    	}
    	else if(lastName == null) {
    		customerList = customerRepository.findByFirstNameContains(firstName);
    	}
    	else {
    		customerList = customerRepository.findByFirstNameContainsAndLastNameContains(firstName, lastName);
    	}
        
        
        if (customerList == null || customerList.size() == 0) {
        	throw new CDMApiException(NO_RECORD_FOUND);
        }
        
        return customerList;
    }
    
    
    
    public Customer saveCustomer(CustomerDetails customerDetails) throws CDMApiException{
    	Customer customer = null;
    	customerValidator.validateCustomerDetails(customerDetails, OPERATION_SAVE);
    	try {
    		customerDetails.setId(null); //clear id before save
    		customer = customerRepository.save(getCustomerEntity(customerDetails));
    	}
    	catch(IllegalArgumentException ex) {
    		throw new CDMApiException(CUSTOMER_SAVE_OPERATION_FAILED);
    	}
    	return customer;
    }

    
    
    public Customer updateCustomer(CustomerDetails customerDetails) throws CDMApiException {
    	Customer customer = null;
    	customerValidator.validateCustomerDetails(customerDetails, OPERATION_UPDATE);
    	try {
    		Customer retrievedCustomer = getCustomerById(customerDetails.getId());
    		if (retrievedCustomer != null) {
    			customer = customerRepository.save(getCustomerEntity(customerDetails));
    		}
    	}
    	catch(IllegalArgumentException ex) {
    		throw new CDMApiException(CUSTOMER_UPDATE_OPERATION_FAILED);
    	}
    	return customer;
    }
    

}
