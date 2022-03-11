package nl.rabobank.cdm.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.rabobank.cdm.data.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {
	
	public List<Customer> findByFirstNameContains(String firstName);
	
	public List<Customer> findByLastNameContains(String lastName);
	
	public List<Customer> findByFirstNameContainsAndLastNameContains(String firstName, String lastName);
	
}
