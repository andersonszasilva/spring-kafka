package br.com.adesozasilva.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.adesozasilva.model.Issue;

@Repository
public interface IssueRepository extends PagingAndSortingRepository<Issue, Long> {

	Issue findByProtocol(String protocol);


}