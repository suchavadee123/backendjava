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
@Entity(name = "product")

public class Product {
	
	@Id
	@GeneratedValue(generator = "product_product_id_seq")
	@Column(name = "product_id", nullable = false)
    private Integer productId;
	
	@Column(name = "product_name")
    private String productName;
	
	@Column(name = "price")
    private Integer price;
	
	@Column(name = "product_type_name")
    private String productTypeName;
	
	@Column(name = "product_model_id")
    private Integer productModelId;
	
	@Column(name = "sweet_level_id")
    private Integer sweetLevelId;
	
	@Column(name = "remake")
    private String remake;
	
	@Column(name = "file")
    private String file;
	
	

}