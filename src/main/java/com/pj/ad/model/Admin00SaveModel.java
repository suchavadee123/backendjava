package com.pj.ad.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Admin00SaveModel {
	
	@JsonProperty("name")
	public String name;

	@JsonProperty("price")
	public Integer price;
	
	@JsonProperty("type")
	public String type;
	
	@JsonProperty("file")
	public String file;

	@JsonProperty("productId")
	public Integer productId;
}
