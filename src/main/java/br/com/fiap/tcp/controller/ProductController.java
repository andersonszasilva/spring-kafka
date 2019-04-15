package br.com.fiap.tcp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.fiap.tcp.model.Freight;
import br.com.fiap.tcp.model.Product;
import br.com.fiap.tcp.repository.ProductRepository;
import br.com.fiap.tcp.service.FreightService;

@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private FreightService freightService;
	private static List<Optional<Product>> wishist = new ArrayList<>();

	@GetMapping(value= "/loadProducts")
	public List<Product> populaBase() {
		List<Product> products = (List<Product>) productRepository.findAll();
		
		if(products.isEmpty()) {
			products = Arrays.asList(new Product("Jogo GTA V", "jogos"),
					new Product("Jogo Fifa 19", "jogos"),
					new Product("Jogo Assassins creed", "jogos"),
					new Product("Livro O Poder do Hábito", "livros"));

			products.forEach(productRepository::save);
		}

		return products;

	}
	
	@PutMapping(value= "/products/wish-list-add/{id}")
	public List<Optional<Product>> addWishList(@PathVariable(value="id") Long id) {
		Optional<Product> product = productRepository.findById(id);
		
		wishist.add(product); 

		return wishist;
	}

	@GetMapping(value= "/products")
	public List<Product> getProductsByCategory(@RequestParam(value="category") String category) {

		return productRepository.findByCategory(category);
	}

	@GetMapping(value= "/product/{id}/details")
	public Optional<Product> getProductById(@PathVariable(value="id") Long id) {

		Optional<Product> product = productRepository.findById(id);
		
		addViewQuantities(product);
		
		return product;
	}
	
	@GetMapping(value= "/products/more-seen-products")
	public List<Product> moreSeenProducts(@RequestParam(value="category") String category) {
		
		List<Product> moreSeenProducts = moreSeenProductsByCategory(category);
		
		return moreSeenProducts;

	}

	private List<Product> moreSeenProductsByCategory(String category) {
		List<Product> moreSeenProducts = productRepository.findByCategory(category);
		moreSeenProducts.sort((p1, p2) -> {
		    return Integer.compare(p2.getViewQuantities(), p1.getViewQuantities()); 
		});
		return moreSeenProducts;
	}

	private void addViewQuantities(Optional<Product> product) {
		Product p = product.get();
		p.setViewQuantities(p.getViewQuantities() + 1);
		
		productRepository.save(p);
	}

	@GetMapping(value= "/products/tags")
	public List<Product> getProductByTag(@RequestParam(value="tag") String tag) {

		return productRepository.findByTag(tag);
	}
	
	@GetMapping(value= "/products/freight")
	public Freight consultaCEP(@RequestParam(value="postalCode") String postalCode) throws JsonParseException, JsonMappingException, IOException {
	   Freight freight = freightService.calculateFreight(postalCode);
       
	   return freight;	

	}

	
	

}
