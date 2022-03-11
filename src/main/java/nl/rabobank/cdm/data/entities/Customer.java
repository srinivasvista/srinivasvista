package nl.rabobank.cdm.data.entities;

import static nl.rabobank.cdm.util.CustomerUtil.calculateAge;
import static nl.rabobank.cdm.util.CustomerUtil.dateToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import nl.rabobank.cdm.model.CustomerDetails;

@Data
@Entity
@Table(name="customer")
public class Customer {
    @Id
    @Column
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    String id;
    @Column
    String firstName;
    @Column
    String lastName;
    @Column
    String address;
    @Column
    Date dob;
    @Column
    Integer age;

    public Customer() {
    	
    }
    
    public Customer(String id, String firstName, String lastName, String address,
			Date dob) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.dob = dob;
		this.age = calculateAge(dob);
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


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer integer) {
		this.age = integer;
	}


}
