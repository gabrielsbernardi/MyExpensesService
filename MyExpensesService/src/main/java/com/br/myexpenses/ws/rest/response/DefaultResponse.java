package com.br.myexpenses.ws.rest.response;

public class DefaultResponse {
	
	private Long id;
	private Boolean isException;
	private String message;
	
	public DefaultResponse() {
		this.isException = Boolean.FALSE;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsException() {
		return isException;
	}
	
	public void setIsException(Boolean isException) {
		this.isException = isException;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}
