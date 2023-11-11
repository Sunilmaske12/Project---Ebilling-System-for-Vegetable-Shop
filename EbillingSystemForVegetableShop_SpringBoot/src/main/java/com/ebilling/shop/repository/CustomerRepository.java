package com.ebilling.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebilling.shop.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
