package com.pj.ad.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pj.ad.repository.core.PageModel;

public class ProductCartSearchModel extends PageModel{

	@JsonProperty("price")
	public Integer price;
	
	@JsonProperty("productId")
	public Integer productId;
	
	@JsonProperty("productName")
	public String productName;
	
	@JsonProperty("historyId")
	public String historyId;
	
	
}
