package com.alacriti.fundtransaction.response;

public class ApiResponse {

	public int status;
	public String message;
	public Object data;

	public ApiResponse(int status, String message, Object object) {

		this.status = status;
		this.message = message;
		this.data = object;

	}

	public ApiResponse(int status, String message) {

		this.status = status;
		this.message = message;

	}
}
