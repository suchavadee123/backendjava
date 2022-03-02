package com.pj.ad.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Data;

@Data
@Entity(name = "product_type")

public class ProductType {
	
	@Id
	@GeneratedValue(generator = "product_type_product_type_id_seq")
	@Column(name = "product_type_id", nullable = false)
    private Integer productTypeId;
	
	@Column(name = "product_type_name")
    private String productTypeName;
	
}