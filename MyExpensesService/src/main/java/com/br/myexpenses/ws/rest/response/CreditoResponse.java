package com.br.myexpenses.ws.rest.response;

import java.util.Date;

public class CreditoResponse extends DefaultResponse {
	
	private String descricao;
	private Double valor;
	private Date data;
	private Integer numParcela;
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public Integer getNumParcela() {
		return numParcela;
	}
	
	public void setNumParcela(Integer numParcela) {
		this.numParcela = numParcela;
	}
	
}
