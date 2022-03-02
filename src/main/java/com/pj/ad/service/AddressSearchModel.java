package com.pj.ad.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pj.ad.repository.core.PageModel;

public class AddressSearchModel extends PageModel{

	@JsonProperty("customerId")
	public Integer customerId;
	
	@JsonProperty("name")
	public Integer name;
	
	@JsonProperty("lastName")
	public String lastName;
	
	@JsonProperty("number")
	public String number;
}
