package br.com.fiap.tcdamazon.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.tcdamazon.model.Issue;

@Repository
public interface IssueRepository extends PagingAndSortingRepository<Issue, Long> {

	Issue findByProtocol(String protocol);


}