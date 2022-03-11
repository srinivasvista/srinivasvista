package nl.rabobank.cdm.exception;

public class CDMValidationException extends CDMApiException{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CDMValidationException(String status, String errorMessage) {
		super(status, errorMessage);
	}

	public CDMValidationException(String errorMessage) {
		super(errorMessage);
	}

}
