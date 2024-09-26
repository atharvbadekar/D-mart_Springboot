package com.springboot.dmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.dmart.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
