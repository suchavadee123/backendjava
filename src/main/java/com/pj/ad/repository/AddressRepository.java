package com.pj.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pj.ad.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

	Address saveAndFlush(Address address);

}