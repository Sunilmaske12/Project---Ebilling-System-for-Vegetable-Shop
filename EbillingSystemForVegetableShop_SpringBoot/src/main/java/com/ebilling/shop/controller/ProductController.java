package com.ebilling.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ebilling.shop.entity.Product;
import com.ebilling.shop.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepository prodRepo;
	
	//get all product of a shop
	@GetMapping("/getAll")
	public List<Product> getAllProduct(){
		return prodRepo.findAll();
	}

	//get all product of a shop
	@GetMapping("/getAll/{shopId}")
	public List<Product> getAllProductByShop(@PathVariable("shopId") int id){
		return prodRepo.findAllById(id);
	}

	//get product by id
	@GetMapping("/get/{id}")
	public Product getProductById(@PathVariable("id") int id){
		return prodRepo.findById(id).get();
	}
	
	//insert product
	@PostMapping("/save")
	public Product saveProduct(@RequestBody Product product) {
		return prodRepo.save(product);
	}
	
}
