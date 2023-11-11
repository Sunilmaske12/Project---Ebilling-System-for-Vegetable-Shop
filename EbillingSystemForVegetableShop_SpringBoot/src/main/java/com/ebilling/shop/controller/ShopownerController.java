package com.ebilling.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ebilling.shop.entity.Shopowner;
import com.ebilling.shop.repository.ShopOwnerRepository;

@RestController
public class ShopownerController {
	
	@Autowired
	private ShopOwnerRepository shopownerRepo;
	
	//get all shopowner
	@GetMapping("/getAllShopOwner")
	public List<Shopowner> getAllShopOwner(){
		return shopownerRepo.findAll();
	}
	
	//get shopowne by id
	@GetMapping("/getShopOwne/{id}")
	public Shopowner getShopownerById(@PathVariable("id") int id) {
		return shopownerRepo.findById(id).get();
	}
	
	//register shopowner
	@PostMapping("/saveShopOwner")
	public Shopowner saveShopOwner(@RequestBody Shopowner shopOwner) {
		return shopownerRepo.save(shopOwner);
	}
}
