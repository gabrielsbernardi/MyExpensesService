package com.br.myexpenses.ws.rest.response;

import java.util.Date;

public class DespesaResponse extends DefaultResponse {
	
	private String descricao;
	private Double valor;
	private Date data;
	private Integer numParcela;
	private String local;
	private DespesaCategoriaResponse categoria;
	
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

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public DespesaCategoriaResponse getCategoria() {
		return categoria;
	}

	public void setCategoria(DespesaCategoriaResponse categoria) {
		this.categoria = categoria;
	}
	
}
