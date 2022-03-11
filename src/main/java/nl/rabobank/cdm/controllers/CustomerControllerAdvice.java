package nl.rabobank.cdm.controllers;

import static nl.rabobank.cdm.constants.CDMConstants.STATUS_FAILURE;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import nl.rabobank.cdm.exception.CDMApiException;
import nl.rabobank.cdm.exception.CDMValidationException;
import nl.rabobank.cdm.model.APIResponse;

@Slf4j
@ControllerAdvice
public class CustomerControllerAdvice {

	@ResponseBody
	@ExceptionHandler(CDMApiException.class)
	public ResponseEntity<APIResponse> handleCDMErrors(final CDMApiException cdmApiException){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse(STATUS_FAILURE, cdmApiException.getMessage()));
	}
	
	@ResponseBody
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<APIResponse> handleOtherExceptions(final HttpRequestMethodNotSupportedException exception){
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new APIResponse("FAILURE", "Wrong HTTP Method used for the operation"));
	}
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse> handleOtherExceptions(final Exception exception){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse("FAILURE", "Exception in Processing"));
	}
}
