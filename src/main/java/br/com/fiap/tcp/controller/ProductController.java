package br.com.fiap.tcp.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tcp.model.Product;
import br.com.fiap.tcp.model.Tag;
import br.com.fiap.tcp.repository.ProductRepository;
import br.com.fiap.tcp.repository.TagRepository;

@RestController
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	private TagRepository tagRepository;
	
	@GetMapping(value= "/populaBase")
	public List<Product> populaBase() {
		List<Product> products = Arrays.asList(new Product("GTA", "game"),
				     new Product("Fifa 19", "game"),
				     new Product("Iphone 8", "game"));
		
		for (Product product : products) {
			productRepository.save(product);
		}
		
		return (List<Product>) productRepository.findAll();
		
	}

	@GetMapping(value= "/products")
	public List<Product> getProductsByCategory(@RequestParam(value="category") String category) {
		
		return productRepository.findByCategory(category);
	}
	
	@GetMapping(value= "/product/{id}")
	public Optional<Product> getProductById(@PathVariable(value="id") Long id) {
		
		return productRepository.findById(id);
	}
	
	@GetMapping(value= "/products")
	public List<Product> getProductByTag(@RequestParam(value="tag") String tag) {
		
		return productRepository.findByTags(tags);
	}
	
	
}
