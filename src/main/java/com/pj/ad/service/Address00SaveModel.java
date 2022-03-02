package com.pj.ad.service;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Address00SaveModel {
	
	@JsonProperty("customerId")
	public Integer customerId;

	@JsonProperty("name")
	public String name;
	
	@JsonProperty("lastName")
	public String lastName;
	
	@JsonProperty("phone")
	public String phone;

}
