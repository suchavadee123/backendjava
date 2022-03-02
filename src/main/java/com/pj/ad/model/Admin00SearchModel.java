package com.pj.ad.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pj.ad.repository.core.PageModel;

import lombok.Data;

@Data
public class Admin00SearchModel extends PageModel{
	
	@JsonProperty("productId")
	public Integer productId;
	
	@JsonProperty("price")
	public Integer price;
	
	@JsonProperty("type")
	public String type;
	
	@JsonProperty("productName")
	public String productName;
	
	
	
}