package br.com.fiap.tcp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import br.com.fiap.tcp.model.Frete;
import br.com.fiap.tcp.model.Product;
import br.com.fiap.tcp.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
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
	
	@GetMapping(value= "/products/frete")
	public Frete consultaCEP(@RequestParam(value="cep") String cep) throws JsonParseException, JsonMappingException, IOException {
	   String REST_URI = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.asmx/CalcPreco?"
			   +"nCdEmpresa="
			   +"&sDsSenha="
               +"&nCdServico=41106" +"&sCepOrigem=02161020"
               +"&sCepDestino=09550250"
               +"&nVlPeso=1"
               +"&nCdFormato=1"
               +"&nVlComprimento=20"
               +"&nVlAltura=20"
               +"&nVlLargura=20"
               +"&nVlDiametro=10"
               +"&sCdMaoPropria=S"
               +"&nVlValorDeclarado=100"
               +"&sCdAvisoRecebimento=N";
	   
	   System.out.println(REST_URI);
	   
	   RestTemplate restTemplate = new RestTemplate();
       String xml = restTemplate.getForObject(REST_URI, String.class);
       
       System.out.println(xml);
       
       XmlMapper xmlMapper = new XmlMapper();
       Frete frete = xmlMapper.readValue(xml, Frete.class);
       
	   return frete;	

	}
	

}
