package com.usermanagement.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiResponse {
	private String message;
	private Integer errorcode;
	public ApiResponse(String message, Integer errorcode) {
		super();
		this.message = message;
		this.errorcode = errorcode;
	}
	
	

}
