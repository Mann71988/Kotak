package com.example.products.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.products.entity.Products;
import com.example.products.repository.ProductsRepository;
import com.example.products.service.ProductsService;



@RestController
@RequestMapping("/api")
public class ProductsController {

	@Autowired
	ProductsService productsService;
	
	 @GetMapping("/getAllProducts")
	  public ResponseEntity<List<Products>> getAllProducts() {
	    try {
	      List<Products> lstProducts = new ArrayList<Products>();
	      lstProducts = productsService.findAll();
	      if (lstProducts.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(lstProducts, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	 
	 @GetMapping("/getAllProductsPage/{pageNo}/{pageSize}")
	  public ResponseEntity<List<Products>> getAllProductsPage(@PathVariable("pageNo") Integer pageNo,
			  @PathVariable("pageSize") Integer pageSize) {
	    try {
	      List<Products> lstProducts = new ArrayList<Products>();
	      lstProducts = productsService.findPagination(pageNo,pageSize);
	      if (lstProducts.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(lstProducts, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
		
	 @GetMapping("/getProductById/{id}")
	  public ResponseEntity<Products> getProductById(@PathVariable("id") Integer id) {
	    Optional<Products> prod = productsService.findById(id);
	    if (prod.isPresent()) {
	      return new ResponseEntity<>(prod.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	@PostMapping("/addProduct")
	  public ResponseEntity<Products> addProduct(@RequestBody Products product) {
	    try {
	    	Products prod = productsService
	          .save(product);
	      return new ResponseEntity<>(prod, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	  @PutMapping("/updateProduct/{id}")
	  public ResponseEntity<Products> updateProducts(@PathVariable("id") Integer id, @RequestBody Products product) {
	    Optional<Products> ProductsData = productsService.findById(id);
	    if (ProductsData.isPresent()) {
	      Products prod = ProductsData.get();
	      prod.setName(product.getName());
	      prod.setDescription(product.getDescription());
	      prod.setPrice(product.getPrice());
	      prod.setOffer_price(product.getOffer_price());
	      return new ResponseEntity<>(productsService.save(prod), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  @DeleteMapping("/deleteProduct/{id}")
	  public ResponseEntity<HttpStatus> deleteProducts(@PathVariable("id") Integer id) {
	    try {
	    	productsService.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @GetMapping("/getProductByQty/{qty}")
	  public ResponseEntity<Products> getProductByQty(
			  @RequestParam("qty") Integer qty) {
	    Optional<Products> prod = productsService.findByQty(qty);
	    if (prod.isPresent()) {
	      return new ResponseEntity<>(prod.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  @GetMapping("/getProductByPrice/{price}")
	  public ResponseEntity<Products> getProductByPrice(
			  @RequestParam("price") Integer price) {
	    Optional<Products> prod = productsService.findByPrice(price);
	    if (prod.isPresent()) {
	      return new ResponseEntity<>(prod.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  @GetMapping("/getProductByoffer_price/{offer_price}")
	  public ResponseEntity<Products> getProductByoffer_price(
			  @RequestParam("offer_price") Integer offer_price) {
	    Optional<Products> prod = productsService.findByoffer_price(offer_price);
	    if (prod.isPresent()) {
	      return new ResponseEntity<>(prod.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	}
