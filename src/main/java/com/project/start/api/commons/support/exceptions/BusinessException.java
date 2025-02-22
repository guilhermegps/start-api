package com.project.start.api.commons.support.exceptions;

public class BusinessException extends RuntimeException {

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Exception e) {
		super(message, e);
	}

}
