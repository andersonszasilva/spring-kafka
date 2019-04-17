package br.com.fiap.tcdamazon.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import br.com.fiap.tcdamazon.model.Freight;

@Service
public class FreightService {

	private final Logger logger = LoggerFactory.getLogger(IssueConsumer.class);

	public Freight calculateFreight(String postalCode) throws IOException, JsonParseException, JsonMappingException {
		String REST_URI = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.asmx/CalcPreco?"
				+"nCdEmpresa="
				+"&sDsSenha="
				+"&nCdServico=41106" +"&sCepOrigem=02161020"
				+"&sCepDestino="+postalCode
				+"&nVlPeso=1"
				+"&nCdFormato=1"
				+"&nVlComprimento=20"
				+"&nVlAltura=20"
				+"&nVlLargura=20"
				+"&nVlDiametro=10"
				+"&sCdMaoPropria=S"
				+"&nVlValorDeclarado=100"
				+"&sCdAvisoRecebimento=N";

		logger.info(String.format("$$ -> Chamando a API dos Correios -> %s", REST_URI));
		RestTemplate restTemplate = new RestTemplate();
		String xml = restTemplate.getForObject(REST_URI, String.class);

		XmlMapper xmlMapper = new XmlMapper();
		Freight freight = xmlMapper.readValue(xml, Freight.class);
		
		return freight;
	}

}
