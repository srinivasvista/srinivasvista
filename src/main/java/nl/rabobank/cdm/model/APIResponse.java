package nl.rabobank.cdm.model;

import lombok.Data;

@Data
public class APIResponse {
	
	private String message;
	private String status;
	
	
	public APIResponse(String status, String message) {
		super();
		this.message = message;
		this.status = status;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
