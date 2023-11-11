package com.ebilling.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebilling.shop.entity.Bill;
import com.ebilling.shop.repository.BillRepository;

@RestController
@RequestMapping("/bill")
public class BillController {
	
	@Autowired
	private BillRepository billRepo;
	
	//get all bill 
	@GetMapping("/getAll")
	public List<Bill> getAllBill(){
		return billRepo.findAll();
	}
	
	//get all bill for a shop
	@GetMapping("/getOfShop/{shopId}")
	public List<Bill> getAllBillByShop(@PathVariable("shopId") int id){
		return billRepo.findAllByShopId(id);
	}

	//get all bill for a customer
	@GetMapping("/getOfCustomer/{custoId}")
	public List<Bill> getAllBillByCustomer(@PathVariable("custoId") int id){
		return billRepo.findByCustomerId(id);
	}

	
	//get all bill for a customer of a shop
	@GetMapping("/getOfCustomer/{shopId}/{custoId}")
	public List<Bill> getAllBillByCustomerAndShop(@PathVariable("shopId") int shopId, @PathVariable("custoId") int custoId){
		return billRepo.findByShopIdAndCustoId();
	}
	//get a bill by id 
	@GetMapping("/get/{id}")
	public Bill getBillById(@PathVariable("id") int id){
		return billRepo.findById(id).get();
	}
	
	//create bill
	@PostMapping("/save")
	public Bill saveBill(@RequestBody Bill bill) {
		return billRepo.save(bill);
	}


}
