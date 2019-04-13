package br.com.fiap.tcp.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="cResultado")
public class Frete {

	@JacksonXmlElementWrapper(localName = "Servicos")
	private List<Result> resultado = new ArrayList<>();

	public void setResultado(List<Result> resultado) {
		this.resultado = resultado;
	}

	public List<Result> getResultado() {
		return resultado;
	}

}