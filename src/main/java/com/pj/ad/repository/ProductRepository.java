package com.pj.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pj.ad.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	Product saveAndFlush(Product product);

}