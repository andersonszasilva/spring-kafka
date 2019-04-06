package br.com.fiap.tcp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.tcp.model.Product;
import br.com.fiap.tcp.model.Tag;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByCategory(@Param("category") String category);
	
	List<Product> findByTags(@Param("tags") List<Tag> tags);

}