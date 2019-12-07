package br.com.adesozasilva.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.adesozasilva.model.Issue;
import br.com.adesozasilva.repository.IssueRepository;

@Service
public class IssueConsumer {
	
	@Autowired
	private IssueRepository issueRepository;
	
	private final Logger logger = LoggerFactory.getLogger(IssueConsumer.class);
	
	@KafkaListener(topics = "issues", groupId = "group_id")
	public void consumeIssue(String json) {
		Gson gson = new Gson();
		Issue issue = gson.fromJson(json, Issue.class);
		logger.info(String.format("$$ -> Consumed Message -> %s", issue.getDescription()));
		
		issueRepository.save(issue);
		
	}
}