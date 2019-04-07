package br.com.fiap.tcp.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tcp.model.Issue;
import br.com.fiap.tcp.repository.IssueRepository;

@RestController
public class IssueController {

	@Autowired
	private IssueRepository issueRepository;

	@GetMapping(value= "/loadIssues")
	public List<Issue> populaBase() {
		List<Issue> issues = (List<Issue>) issueRepository.findAll();
		if(issues.isEmpty()) {
			issues = Arrays.asList(new Issue("Não estou conseguindo efetuar a compra"),
					new Issue("Produto indisponível quando finaliza a compra"));

			issues.forEach(issueRepository::save);
		}
		
		return (List<Issue>) issueRepository.findAll();

	}

	@PostMapping(value= "/issues")
	public Issue saveIssue(@RequestBody Issue issue) {

		return issueRepository.save(issue);
	}


}
