package nl.rabobank.cdm.util;

import static nl.rabobank.cdm.constants.CDMConstants.PATTERN_CUSTOMER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.Test;

public class CustomerUtilTest {
	
	
	@Test
	public void test_is_null_or_empty_null_value(){
		assertEquals(true, CustomerUtil.isNullOrEmpty(null));
		
	}
	
	@Test
	public void test_is_null_or_empty_empty_value(){
		assertEquals(true, CustomerUtil.isNullOrEmpty(""));
		
	}
	
	@Test
	public void test_is_null_or_empty_valid_value(){
		assertEquals(false, CustomerUtil.isNullOrEmpty("test"));
		
	}
	
	@Test
	public void test_is_not_valid_pattern_invalid_value(){
		assertEquals(true, CustomerUtil.isNotValidPattern("123e4567-e89b-12d3-a456-4261417400", PATTERN_CUSTOMER_ID));
		
	}
	
	@Test
	public void test_is_not_valid_pattern_valid_value(){
		assertEquals(false, CustomerUtil.isNotValidPattern("123e4567-e89b-12d3-a456-426614174000", PATTERN_CUSTOMER_ID));
		
	}
	
	@Test
	public void test_string_to_date_invalid_value(){
		assertNull(CustomerUtil.stringToDate("30-02-2000"));
		
	}
	
	@Test
	public void test_string_to_date_valid_value(){
		assertNotNull(CustomerUtil.stringToDate("29-02-2000"));
		
	}
	
	@Test
	public void test_calc_age(){
		assertEquals(22, CustomerUtil.calculateAge(CustomerUtil.stringToDate("01-01-2000")));
	}
	
}
