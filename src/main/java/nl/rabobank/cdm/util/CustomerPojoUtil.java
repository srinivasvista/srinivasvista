package nl.rabobank.cdm.util;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import static nl.rabobank.cdm.util.CustomerUtil.dateToString;
import static nl.rabobank.cdm.util.CustomerUtil.stringToDate;

import nl.rabobank.cdm.data.entities.Customer;
import nl.rabobank.cdm.model.CustomerDetails;

public class CustomerPojoUtil {
	
	public static CustomerDetails getCustomerDetails(Customer customer) {
    	return new CustomerDetails(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getAddress(), dateToString(customer.getDob()), String.valueOf(customer.getAge()));
	}

    public static List<CustomerDetails> getCustomerDetailsList(List<Customer> customerList) {
    	List<CustomerDetails> customerDetailsList = customerList.stream()
    															.map(customer -> getCustomerDetails(customer))
    															.collect(Collectors.toList());
    	return customerDetailsList;
    }

    public static Customer getCustomerEntity(CustomerDetails customerDetails) {
		Date dob = stringToDate(customerDetails.getDob());
		return new Customer(customerDetails.getId(), customerDetails.getFirstName(), customerDetails.getLastName(), customerDetails.getAddress(), dob);
	}

}
