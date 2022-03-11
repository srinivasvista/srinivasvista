package nl.rabobank.cdm.controllers;

import static nl.rabobank.cdm.util.CustomerPojoUtil.getCustomerDetails;
import static nl.rabobank.cdm.util.CustomerPojoUtil.getCustomerDetailsList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import nl.rabobank.cdm.exception.CDMApiException;
import nl.rabobank.cdm.model.CustomerDetails;
import nl.rabobank.cdm.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping(value = "/customer")
	public List<CustomerDetails> getCustomers() throws CDMApiException {
		return getCustomerDetailsList(customerService.getCustomers());
	}

	@GetMapping(value = "/customer/id/{id}")
	public CustomerDetails getCustomerById(@PathVariable String id) throws CDMApiException {
		return getCustomerDetails(customerService.getCustomerById(id));
	}

	@GetMapping(value = "/customer/search")
	public List<CustomerDetails> getCustomerByFieldName(@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName) throws CDMApiException {
		return getCustomerDetailsList(customerService.getCustomerByFieldName(firstName, lastName));
	}

	@PostMapping(value = "/customer")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDetails saveCustomer(@RequestBody CustomerDetails customerDetails) throws CDMApiException {
		return getCustomerDetails(customerService.saveCustomer(customerDetails));

	}

	@PutMapping(value = "/customer")
	public CustomerDetails updateCustomer(@RequestBody CustomerDetails customerDetails) throws CDMApiException {
		return getCustomerDetails(customerService.updateCustomer(customerDetails));
	}

}
