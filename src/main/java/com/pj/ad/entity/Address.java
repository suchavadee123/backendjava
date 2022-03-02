package com.pj.ad.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.util.Streamable;

import lombok.Data;

@Data
@Entity(name = "address")

public class Address {
	
	@Id
	@GeneratedValue(generator = "address_customer_id_seq")
	@Column(name = "customer_id", nullable = false)
    private Integer customerId;
	
	@Column(name = "name")
    private String name;
	
	@Column(name = "last_name")
    private String lastName;
	
	@Column(name = "number")
    private String number;
	
	}
	
