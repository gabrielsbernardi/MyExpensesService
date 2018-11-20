package com.br.myexpenses.ws.rest.request;

import java.util.Date;


public class CreditoRequest extends DefaultRequest {
	
	private Long usuario;
	private Long id;
	private Integer numParcelas;
	private String descricao;
	private Double valor;
	private Date data;
	
	public Long getUsuario() {
		return usuario;
	}
	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNumParcelas() {
		return numParcelas;
	}
	public void setNumParcelas(Integer numParcelas) {
		this.numParcelas = numParcelas;
	}
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
	
	
}
