package com.pj.ad.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pj.ad.exception.BusinessLogicException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ControllerConfig extends ResponseEntityExceptionHandler {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringtrimmer);
	}

	@ExceptionHandler({ Exception.class })
	protected ResponseEntity<Object> handleOther(Exception ex, WebRequest request) {
		log.error(ex.getMessage(), ex);
		Map<String, Object> body = new HashMap<>();
		body.put("success", false);
		body.put("type", "error");
		body.put("title", "Error");
		body.put("message", "Please contact admin!");
		return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.OK, request);
	}

	@ExceptionHandler({ BusinessLogicException.class })
	protected ResponseEntity<Object> handleOther(BusinessLogicException ex, WebRequest request) {
		log.error(ex.getMessage(), ex);
		Map<String, Object> body = new HashMap<>();
		body.put("success", false);
		body.put("type", ex.getType());
		body.put("title", ex.getTitle());
		body.put("message", ex.getMessage());
		return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.OK, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		log.error(ex.getMessage(), ex);
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	

}
