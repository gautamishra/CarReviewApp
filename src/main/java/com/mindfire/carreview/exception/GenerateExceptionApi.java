package com.mindfire.carreview.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception Generator Class For Users
 * 
 * @author mindfire
 *
 */

public class GenerateExceptionApi extends Exception {

	private String message;
	private HttpStatus httpstatus;

	public GenerateExceptionApi() {

	}

	public GenerateExceptionApi(String message, HttpStatus httpstatus) {
		this.message = message;
		this.httpstatus = httpstatus;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpstatus() {
		return httpstatus;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setHttpstatus(HttpStatus httpstatus) {
		this.httpstatus = httpstatus;
	}

	

}
