package com.ebilling.shop.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ebilling.shop.entity.Product;
import com.ebilling.shop.entity.Shopowner;
import com.ebilling.shop.exception.ResourceNotFoundException;
import com.ebilling.shop.repository.ProductRepository;
import com.ebilling.shop.repository.ShopOwnerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepository prodRepo;
	
	@Autowired 
	private ShopOwnerRepository shopRepo;
	
	@Autowired
	private ObjectMapper mapper;
	
	String mainPath ="D:\\STS_Work\\backend-images\\";
	
	
	
	//get all product of a shop
	@GetMapping("/getAll")
	public List<Product> getAllProduct(){
		
		return prodRepo.findAll();
	}

	//get all product of a shop
	@GetMapping("/getAll/{shopId}")
	public List<Product> getAllProductByShop(@PathVariable("shopId") int id){
		return prodRepo.findAllByShopownerId(id);
	}

	//get product by id
	@GetMapping("/get/{id}")
	public Product getProductById(@PathVariable("id") int id){
		Optional<Product> product = prodRepo.findById(id);
		if(product.isEmpty()) {
				throw new ResourceNotFoundException("Product is not found with id : "+id);
		}
		return product.get();
	}
	
	//insert product
	@PostMapping("/save/{shopId}")
	public Product saveProduct(@PathVariable("shopId") int shopId,@RequestParam("product") String productData,  @RequestParam("image") MultipartFile file) throws IOException {
		
		//converting string to json
		Product product = null;
		
		try {
			product = mapper.readValue(productData, Product.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}	
		
		Optional<Shopowner> optionalShop = shopRepo.findById(shopId);
		if(!optionalShop.isEmpty()) {
			product.setShopowner(optionalShop.get());
		}
		if(file.isEmpty()) {
			product.setImageName("vegetable.jpg");
		}
		else {
			
			product.setImageName(file.getOriginalFilename());
			
			//saving image to folder
			InputStream is=file.getInputStream();
			Path path = Paths.get(mainPath+"VMS"+File.separator+file.getOriginalFilename());
				
			Files.copy(is, path, StandardCopyOption.REPLACE_EXISTING);
		}
		
		return prodRepo.save(product);
	}
	
	
	//update product
	@PutMapping("/{sid}/updateProduct")
	public Product updateProduct(@RequestBody Product product, @PathVariable("sid") int sid) {
		Optional<Shopowner> optionalShop = shopRepo.findById(sid);
		System.out.println("hello");
		if(!optionalShop.isEmpty()) {
			product.setShopowner(optionalShop.get());
		}
		return prodRepo.save(product);
	}
	
	//delete product
	@DeleteMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable("id") int id) {
		System.out.println(id);
	Optional<Product> product = prodRepo.findById(id);
		if(product.isPresent()) {
			prodRepo.delete(product.get());
			return "product deleted succesfully";
		}
		else {
			throw new ResourceNotFoundException("Product is not found with id : "+id);
		}
	}
	
	//get product Image
	@GetMapping(value="image/{id}", produces=MediaType.IMAGE_JPEG_VALUE)
	public void getImage(@PathVariable("id") int id, HttpServletResponse res) throws IOException {
		
		Optional<Product> product = prodRepo.findById(id);
		if(product.isPresent()) {
			String imageName = product.get().getImageName();
			if(imageName!=null) {
				String fullPath = mainPath+"VMS"+File.separator+imageName;
				InputStream is = new FileInputStream(fullPath);
				res.setContentType(MediaType.IMAGE_JPEG_VALUE);
				StreamUtils.copy(is, res.getOutputStream());
			}
		}
	}

	
	
}
