package br.com.fiap.tcp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="cServico")
public class Result {

	@JacksonXmlProperty(localName="Codigo")
	private String codigo;
	@JacksonXmlProperty(localName="Valor")
	private String valor;
	@JacksonXmlProperty(localName="ValorMaoPropria")
	private String valorMaoPropria;
	@JacksonXmlProperty(localName="ValorAvisoRecebimento")
	private String valorAvisoRecebimento;
	@JacksonXmlProperty(localName="ValorValorDeclarado")
	private String valorDeclarado;
	@JacksonXmlProperty(localName="Erro")
	private String erro;
	@JacksonXmlProperty(localName="MsgErro")
	private String msgErro;
	@JacksonXmlProperty(localName="ValorSemAdicionais")
	private String valorSemAdicionais;
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getValorMaoPropria() {
		return valorMaoPropria;
	}
	public void setValorMaoPropria(String valorMaoPropria) {
		this.valorMaoPropria = valorMaoPropria;
	}
	public String getValorAvisoRecebimento() {
		return valorAvisoRecebimento;
	}
	public void setValorAvisoRecebimento(String valorAvisoRecebimento) {
		this.valorAvisoRecebimento = valorAvisoRecebimento;
	}
	
	public void setValorDeclarado(String valorDeclarado) {
		this.valorDeclarado = valorDeclarado;
	}
	
	public String getValorDeclarado() {
		return valorDeclarado;
	}
	
	public String getErro() {
		return erro;
	}
	public void setErro(String erro) {
		this.erro = erro;
	}
	public String getMsgErro() {
		return msgErro;
	}
	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}
	public String getValorSemAdicionais() {
		return valorSemAdicionais;
	}
	public void setValorSemAdicionais(String valorSemAdicionais) {
		this.valorSemAdicionais = valorSemAdicionais;
	}



}