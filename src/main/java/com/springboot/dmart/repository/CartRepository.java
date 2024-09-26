package com.springboot.dmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.dmart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

}
