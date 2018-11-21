package com.br.myexpenses.ws.rest.response;

import java.util.Date;

import com.br.myexpenses.utils.Utils;

public class CreditoResponse extends DefaultResponse implements Comparable<CreditoResponse> {
	
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

	public int compareTo(CreditoResponse o) {
		if (Utils.stringIsNull(this.getDescricao()) || Utils.stringIsNull(o.getDescricao())) {
		      return 0;
		}
		
	    return this.getDescricao().compareTo(o.getDescricao());
	}
	
}
