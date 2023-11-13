package com.ebilling.shop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ebilling.shop.entity.Customer;
import com.ebilling.shop.entity.Shopowner;
import com.ebilling.shop.repository.CustomerRepository;
import com.ebilling.shop.repository.ShopOwnerRepository;

@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private ShopOwnerRepository shopownerRepo;

	//get all customer
	@GetMapping("/getAllCustomer")
	public List<Customer> getAllCustomer(){
		return customerRepo.findAll();
	}
	
	//get all customer of a shop
	@GetMapping("/getAll/{shopId}")
	public List<Customer> getAllCustomerOfShop(@PathVariable("shopId") int id){
		return customerRepo.findAllByShopownerId(id);
	}
	
	//Get One Customer By Id
	@GetMapping("/getCustomer/{id}")
	public Customer getCustomerById(@PathVariable("id") int id) {
		return customerRepo.findById(id).get();
	}
	
	
	//create customer
	@PostMapping("saveCustomer/{id}")
	public Customer saveCustomer(@RequestBody Customer customer, @PathVariable("id") int id) {
		Optional<Shopowner> shopowner1 = shopownerRepo.findById(id);
		if(!shopowner1.isEmpty()) {
			Shopowner shopowner2=shopowner1.get();
			customer.setShopowner(shopowner2);			
		}
		return customerRepo.save(customer);
		
	}
	
	
}
