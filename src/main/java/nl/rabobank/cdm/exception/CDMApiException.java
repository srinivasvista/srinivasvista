package nl.rabobank.cdm.exception;

public class CDMApiException extends Exception{
	
	/**
	 * 
	 */
private static final long serialVersionUID = 1L;
	
	private String status;

	public CDMApiException(String status, String errorMessage) {
		super(errorMessage);
		this.status = status;
	}
	
	public CDMApiException(String errorMessage) {
		super(errorMessage);
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
