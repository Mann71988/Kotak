package com.example.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.products.entity.Products;
import com.example.products.repository.ProductsRepository;

@Service
public class ProductsService {

	@Autowired
	private ProductsRepository productsRepository;

	public List<Products> findAll() {
		
		return productsRepository.findAll();
	}
	
	public List<Products>  findPagination(int pageno, int pagesize){
		PageRequest pageRequest = PageRequest.of(0, 3);
		Page<Products> pageResult = productsRepository.findAll(pageRequest);
		return pageResult.toList();
	}

	public Optional<Products> findById(Integer id) {
		return productsRepository.findById(id);
		
	}

	public Products save(Products product) {
		return productsRepository.save(product);
		
	}

	public void deleteById(Integer id) {
		productsRepository.deleteById(id);
		
	}

	public Optional<Products> findByQty(Integer qty) {
		return productsRepository.findByQty(qty);
		
	}

	public Optional<Products> findByPrice(Integer price) {
		Optional<Products> prodObj = productsRepository.findByPrice(price);
		return prodObj;
		
	}

	public Optional<Products> findByoffer_price(Integer offer_price) {
		Optional<Products> prodObj = productsRepository.findByoffer_price(offer_price);
		return prodObj;
	}
}
