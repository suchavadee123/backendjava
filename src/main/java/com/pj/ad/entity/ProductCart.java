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
@Entity(name = "productcart")

public class ProductCart {
	
	@Id
	@GeneratedValue(generator = "db_history_history_id_seq")
	@Column(name = "history_id", nullable = false)
    private Integer historyId;
	
	@Column(name = "order_on")
    private String orderOn;
	
	@Column(name = "product_id")
    private Integer productId;
	
	@Column(name = "sweet")
    private String sweet;
	
	@Column(name = "product_type_name")
    private String productTypeName;
	
	@Column(name = "comment")
    private String comment;
	
	@Column(name = "count")
    private Integer count;
	
	@Column(name = "product_name")
    private String productName;

	}
	
