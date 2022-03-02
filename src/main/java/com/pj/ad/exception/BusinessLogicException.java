package com.pj.ad.exception;

import lombok.Getter;

@Getter
public class BusinessLogicException extends RuntimeException {

	private static final long serialVersionUID = 5017634860143464076L;
	
	private String title;
	private String type;
	private String message;
	
	private BusinessLogicException(String type, String title, String message) {
		this.type = type;
		this.title = title;
		this.message = message;
	}
	
	public static BusinessLogicException error(String title, String message) {
		return new BusinessLogicException("error", title, message);
	}
	
	public static BusinessLogicException error(String message) {
		return error("Error", message);
	}
	
	public static BusinessLogicException warning(String title, String message) {
		return new BusinessLogicException("warning", title, message);
	}

	public static BusinessLogicException warning(String message) {
		return warning("Warning", message);
	}

}
