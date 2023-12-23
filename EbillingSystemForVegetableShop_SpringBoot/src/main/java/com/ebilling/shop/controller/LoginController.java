package com.ebilling.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ebilling.shop.entity.Customer;
import com.ebilling.shop.entity.Shopowner;
import com.ebilling.shop.repository.CustomerRepository;
import com.ebilling.shop.repository.ShopOwnerRepository;

@RestController
@CrossOrigin
public class LoginController {
	
	@Autowired
	private ShopOwnerRepository shopRepo;
	
	@Autowired
	private CustomerRepository custoRepo;
	
	
	@GetMapping("/login/customer/{email}/{pass}")
	public Customer loginCustomer(@PathVariable("email") String email, @PathVariable("pass") String password) {
		System.out.println("Inside cust");
		Customer customer=custoRepo.findByEmail(email);
		if(customer!=null) {
			System.out.println("Email");
			if(customer.getPassword().equals(password)) {
				System.out.println("Password");
				return customer;
			}
		}
		return null;
	}
	
	@GetMapping("/login/shopowner/{email}/{pass}")
	public Shopowner loginShopowner(@PathVariable("email") String email, @PathVariable("pass") String password) {
		Shopowner shop = shopRepo.findByEmail(email);
		if(shop!=null) {
			System.out.println("shop "+shop);
			if(shop.getPassword().equals(password)) {
				System.out.println("Match");
				return shop;
			}
		}
		return null;
	}


}
