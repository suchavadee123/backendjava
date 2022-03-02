package com.pj.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pj.ad.entity.Product;
import com.pj.ad.entity.ProductCart;

public interface ProductCartRepository extends JpaRepository<ProductCart, Integer>{

	ProductCart saveAndFlush(ProductCart productcart);

}