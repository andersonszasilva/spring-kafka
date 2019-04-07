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
import br.com.fiap.tcp.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

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

	@GetMapping(value= "/products")
	public List<Product> getProductsByCategory(@RequestParam(value="category") String category) {

		return productRepository.findByCategory(category);
	}

	@GetMapping(value= "/product/{id}")
	public Optional<Product> getProductById(@PathVariable(value="id") Long id) {

		return productRepository.findById(id);
	}

	@GetMapping(value= "/products/tags")
	public List<Product> getProductByTag(@RequestParam(value="tag") String tag) {

		return productRepository.findByTag(tag);
	}


}
