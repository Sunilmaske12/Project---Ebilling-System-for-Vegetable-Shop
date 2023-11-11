package com.ebilling.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ebilling.shop.entity.Customer;
import com.ebilling.shop.repository.CustomerRepository;

@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepo;

	//get all customer
	@GetMapping("/getAllCustomer")
	public List<Customer> getAllCustomer(){
		return customerRepo.findAll();
	}
	
	//Get One Customer By Id
	@GetMapping("/getCustomer/{id}")
	public Customer getCustomerById(@PathVariable("id") int id) {
		return customerRepo.findById(id).get();
	}
	
	//create customer
	@PostMapping("saveCustomer")
	public Customer saveCustomer(@RequestBody Customer customer) {
		return customerRepo.save(customer);
	}
	
	
}
