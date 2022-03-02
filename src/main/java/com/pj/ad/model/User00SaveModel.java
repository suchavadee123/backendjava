package com.pj.ad.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class User00SaveModel {
	
	@JsonProperty("name")
	public String name;

	@JsonProperty("price")
	public Integer price;
	
	@JsonProperty("type")
	public String type;
	
	@JsonProperty("sweet")
	public String sweet;
	
	@JsonProperty("count")
	public Integer count;
	
	@JsonProperty("comment")
	public String comment;
	
	@JsonProperty("historyId")
	public Integer historyId;
	
	@JsonProperty("productId")
	public Integer productId;
}
