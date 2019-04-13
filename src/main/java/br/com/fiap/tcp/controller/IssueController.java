package br.com.fiap.tcp.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tcp.model.Issue;
import br.com.fiap.tcp.model.Message;
import br.com.fiap.tcp.repository.IssueRepository;
import br.com.fiap.tcp.service.IssueProducer;

@RestController
public class IssueController {

	private final IssueProducer producer;

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	public IssueController(IssueProducer producer) {
		this.producer = producer;
	}

	@PostMapping(value = "/chamados")
	public ResponseEntity<Message> criaChamadoComKafka(@RequestBody Issue chamado) {
		Message mensagem = null;
		try {
			UUID uuid = UUID.randomUUID();
			chamado.setProtocol(uuid.toString());
			this.producer.sendMessageIssue(chamado);
			mensagem = new Message("Seu chamado foi criado com sucesso. Anote o seu protocolo: " + chamado.getProtocol());
			return new ResponseEntity<Message>(mensagem, HttpStatus.CREATED);

		} catch (Exception e) {
			mensagem = new Message("Erro ao processar a sua solicitação!");
			return new ResponseEntity<Message>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	
	@GetMapping(value= "/chamados")
	public Issue procuraChamadoPorProtocolo(@RequestParam(value="protocolo") String protocolo) {
		Issue issue = issueRepository.findByProtocolo(protocolo);
		return issue;
	}


}