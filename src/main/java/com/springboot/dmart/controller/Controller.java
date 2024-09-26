package com.springboot.dmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dmart.entity.Cart;
import com.springboot.dmart.entity.Product;
import com.springboot.dmart.repository.CartRepository;
import com.springboot.dmart.repository.ProductRepository;

@RestController
public class Controller {
	@Autowired
	private ProductRepository repo;
	@Autowired
	private CartRepository crepo;
	
	@PostMapping("/product")
	public Product addProduct(@RequestBody Product product) {
		return repo.save(product);
	}
	
	@GetMapping("/products")
	public List<Product> getALlProduct(){
		return repo.findAll();
	}
	
	@GetMapping("/cart")
	public List<Cart> getAllCart(){
		return crepo.findAll();
	}
	
	@GetMapping("/product/id={id}")
	public Product getById(@PathVariable int id) {
		return repo.findById(id).get();
	}
	
	@PostMapping("/cart/id={id}/qt={quantity}")
	public Cart addToCart(@PathVariable int id, @PathVariable int quantity) {
		Cart c = new Cart();
		c.setProductId(id);
		c.setQuantity(quantity);
		return crepo.save(c);
	}
	
	@DeleteMapping("/cart/id={id}")
	public Object removeFromCart(@PathVariable int id) {
		crepo.deleteById(id);
		return id;
	}
	
	@DeleteMapping("/product/id={id}")
	public Object removeFromProducts(@PathVariable int id) {
		repo.deleteById(id);
		return id;
	}
	
	@GetMapping("/bill")
	public String printBill() {
		List<Cart> cart = crepo.findAll();
		double bill = 0;
		String s = "";
		for(Cart c:cart) {
			Product p = repo.findById(c.getProductId()).get();
			bill = bill + (p.getPrice()*c.getQuantity());
			s += "{"+"\n Id : "+c.getId()+" \n Name : "+p.getName()+"  \n Price : "+p.getPrice()+" \n Quantity : "+c.getQuantity()+"\n}\n";
		}
		return s + "Your Total Bill is :   Rs."+ bill;
	}
	
	

}
