package br.com.fiap.tcp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.tcp.model.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

	List<Product> findByCategory(String category);
	
	@Query("select p from Product p where p.description like %?1% or p.category like %?1%")
	List<Product> findByTag(String tag);
	

}