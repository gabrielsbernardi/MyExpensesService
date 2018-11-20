package com.br.myexpenses.ws.rest.request;

import java.sql.Date;

public class DespesaRequest extends DefaultRequest {

	private Long id;
	private Integer parcela;
	private String localCompra;
	private String descricao;
	private Date dataCompra;
	private Long categoria;
	private double valor;
	
	
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
	
	public String getLocalCompra() {
		return localCompra;
	}
	
	public void setLocalCompra(String localCompra) {
		this.localCompra = localCompra;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Date getDataCompra() {
		return dataCompra;
	}
	
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
	
	public Long getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
}
