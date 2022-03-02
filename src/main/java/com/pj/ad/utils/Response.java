package com.pj.ad.utils;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.pj.ad.repository.core.Data;
import com.pj.ad.repository.core.GridData;

public class Response extends ResponseEntity<Map<String, Object>> {

	public Response(HttpStatus status) {
		super(status);
	}

	public Response(Map<String, Object> body, HttpStatus status) {
		super(body, status);
	}

	public Response(Map<String, Object> body, MultiValueMap<String, String> headers, HttpStatus status) {
		super(body, headers, status);
	}

	public static Response success(GridData gridData) {
		return Response.success(gridData, Data.of());
	}

	public static Response success(GridData gridData, Data data) {
		Map<String, Object> content = new HashMap<>();
		content.put("records", gridData.getRecords());
		content.put("success", true);
		content.put("total", gridData.getTotal());
		if (CoreUtils.isNotEmpty(data.getData())) {
			content.put("data", data.getData());
		}
		return new Response(content, HttpStatus.OK);
	}

	public static Response success(Data data) {
		Map<String, Object> content = new HashMap<>();
		content.put("data", data.getData());
		content.put("success", true);
		return new Response(content, HttpStatus.OK);
	}
	
	public static Response success(Data data1,Data data2,Data data3) {
		Map<String, Object> content = new HashMap<>();
		content.put("data1", data1.getData());
		content.put("data2", data2.getData());
		content.put("data3", data3.getData());
		content.put("success", true);
		return new Response(content, HttpStatus.OK);
	}

	public static Response success() {
		Map<String, Object> content = new HashMap<>();
		content.put("success", true);
		return new Response(content, HttpStatus.OK);
	}

	public static Response success(String result) {
		Map<String, Object> content = new HashMap<>();
		content.put("success", true);
		content.put("result", result);
		return new Response(content, HttpStatus.OK);
	}

	public static Response error(String error) {
		Map<String, Object> content = new HashMap<>();
		content.put("success", false);
		content.put("message", error);
		return new Response(content, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public static Response fail() {
		Map<String, Object> content = new HashMap<>();
		content.put("success", false);
		return new Response(content, HttpStatus.OK);
	}

}
