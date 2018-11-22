package com.br.myexpenses.ws.rest.request;

import java.util.Date;

public class LancamentoRequest extends DefaultRequest {
	
	private Date dataCompleta;
	private String data;
	private Double valor;
	private Double totalCredito;
	private Double totalDespesa;
	private Boolean isSearch;
	
	public Date getDataCompleta() {
		return dataCompleta;
	}
	
	public void setDataCompleta(Date dataCompleta) {
		this.dataCompleta = dataCompleta;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Double getTotalCredito() {
		return totalCredito;
	}
	
	public void setTotalCredito(Double totalCredito) {
		this.totalCredito = totalCredito;
	}
	
	public Double getTotalDespesa() {
		return totalDespesa;
	}
	
	public void setTotalDespesa(Double totalDespesa) {
		this.totalDespesa = totalDespesa;
	}

	public Boolean getIsSearch() {
		return isSearch;
	}

	public void setIsSearch(Boolean isSearch) {
		this.isSearch = isSearch;
	}
	
}
