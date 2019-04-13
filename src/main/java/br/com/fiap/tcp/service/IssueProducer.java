package br.com.fiap.tcp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.fiap.tcp.model.Issue;

@Service
public class IssueProducer {
	
	private static final Logger logger = LoggerFactory.getLogger(IssueProducer.class);
	private static final String TOPIC_ISSUES = "issues";
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessageIssue(Issue issue) {
		logger.info(String.format("$$ -> Producing message --> %s", issue.getDescription()));
		Gson gson = new Gson();
		String json = gson.toJson(issue);
		
		this.kafkaTemplate.send(TOPIC_ISSUES, json);
	}
}
