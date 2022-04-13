package com.example.products.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.products.entity.Products;

public interface ProductsRepository extends JpaRepository<Products, Integer>{

	Optional<Products> findByQty(Integer qty);

	Optional<Products> findByPrice(Integer price);

	Optional<Products> findByoffer_price(Integer offer_price);

}
