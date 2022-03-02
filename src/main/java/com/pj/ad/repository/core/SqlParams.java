package com.pj.ad.repository.core;

import java.util.HashMap;
import java.util.Map;

public class SqlParams {

Map<String, Object> params;
	
	private SqlParams() {
		params = new HashMap<>();
	}
	
	public static SqlParams create() {
		return new SqlParams();
	}
	
	public static SqlParams create(String name, Object value) {
		return new SqlParams().add(name, value);
	}
	
	public static SqlParams create(PageModel model) {
		SqlParams params = new SqlParams();
		params.params.put("start_", model.getPageNumber() - 1);
		params.params.put("limit_", model.getPageSize());
		params.params.put("offset_", (model.getPageNumber() - 1) * model.getPageSize());
		return params;
	}
	
	public SqlParams add(String name, Object value) {
		this.params.put(name, value);
		return this;
	}
	
	Map<String, Object> getParams() {
		return this.params;
	}
}
