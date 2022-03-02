package com.pj.ad.service;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductCartDetailModel{
	
	@JsonProperty("sweet")
	public String sweet;
	
	@JsonProperty("comment")
	public String comment;
	
	@JsonProperty("count")
	public Integer count;
	
	@JsonProperty("historyId")
	public Integer historyId;
	
	@JsonProperty("productId")
	public Integer productId;
	
	@JsonProperty("productName")
	public String productName;
	
	@JsonProperty("price")
	public Integer price;
	
	public static class GetDetail {
		@JsonProperty("historyId")
		public Integer historyId;
		
		
		
	}
}
