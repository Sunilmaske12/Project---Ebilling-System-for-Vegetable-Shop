package com.ebilling.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebilling.shop.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer> {

	List<Bill> findAllById(int id);

	List<Bill> findByShopIdAndCustoId();

	List<Bill> findByCustomerId(int id);

	List<Bill> findAllByShopId(int id);

}
