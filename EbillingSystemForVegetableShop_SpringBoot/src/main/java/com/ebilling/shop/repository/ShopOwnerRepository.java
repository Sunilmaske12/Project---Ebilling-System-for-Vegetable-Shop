package com.ebilling.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebilling.shop.entity.Shopowner;

public interface ShopOwnerRepository extends JpaRepository<Shopowner, Integer> {

}
