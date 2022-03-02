package com.pj.ad.service;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Admin00DetailModel{

	@JsonProperty("name")
	public String name;

	@JsonProperty("price")
	public Integer price;
//	
	@JsonProperty("type")
	public String type;
	
	@JsonProperty("productId")
	public Integer productId;
	
	@JsonProperty("file")
	public String file;
	
	@Data
	public static class GetDetail {
		@JsonProperty("productId")
		public Integer productId;
		
	}
}
