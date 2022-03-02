package com.pj.ad.repository.core;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.pj.ad.utils.CoreUtils;

import lombok.Getter;

@lombok.Data
@Getter
public class Data implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> data;

	private Data() {
	}

	public static Data of() {
		Data d = new Data();
		return d;
	}

	public static Data of(Map<String, Object> data) {
		Data d = new Data();
		d.data = data;
		return d;
	}

	public static Data of(List<Map<String, Object>> records) {
		Data d = new Data();
		d.data = (CoreUtils.isNotEmpty(records)) ? records.get(0) : null;
		return d;
	}

	public Data put(String key, Object value) {
		this.data.put(key, value);
		return this;
	}

	public Object get(String key) {
		return this.data.get(key);
	}

	public Data remove(String key) {
		this.data.remove(key);
		return this;
	}

	@Override
	public String toString() {
	return "Data [" + data + "]";
	}

}
