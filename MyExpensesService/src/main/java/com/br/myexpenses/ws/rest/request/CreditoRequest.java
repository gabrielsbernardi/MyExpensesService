package com.br.myexpenses.ws.rest.request;

import java.util.Date;


public class CreditoRequest extends DefaultRequest {
	
	private Long usuario;
	private Long id;
	private Integer parcela;
	private String descricao;
	private Double valor;
	private Date data;
	private Boolean isSearch;
	
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
	public Integer getParcela() {
		return parcela;
	}
	public void setParcela(Integer parcela) {
		this.parcela = parcela;
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
	public Boolean getIsSearch() {
		return isSearch;
	}
	public void setIsSearch(Boolean isSearch) {
		this.isSearch = isSearch;
	}
	
}
