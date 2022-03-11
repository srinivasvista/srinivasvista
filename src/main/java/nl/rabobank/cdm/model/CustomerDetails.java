package nl.rabobank.cdm.model;

import lombok.Data;

@Data
public class CustomerDetails {
   
    String id;
    String firstName;
    String lastName;
    String address;
    String dob;
    String age;
    
    public CustomerDetails() {
    	
    }
    
	public CustomerDetails(String id, String firstName, String lastName, String address,
			String dob, String age) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.dob = dob;
		this.age = age;
	}
	
	public CustomerDetails(String id, String firstName, String lastName, String address,
			String dob) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.dob = dob;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}



}
