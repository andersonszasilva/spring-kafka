package br.com.fiap.tcp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.tcp.model.Tag;

@Repository
public interface TagRepository extends PagingAndSortingRepository<Tag, Long> {
	
	Tag findByName(String name);

}
