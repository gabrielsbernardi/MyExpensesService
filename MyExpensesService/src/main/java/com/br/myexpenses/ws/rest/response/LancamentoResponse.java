package com.br.myexpenses.ws.rest.response;

import java.util.Date;
import java.util.List;

public class LancamentoResponse extends DefaultResponse implements Comparable<LancamentoResponse> {
	
	private Date dataCompleta;
	private String data;
	private Double valor;
	private Double totalCredito;
	private Double totalDespesa;
	private List<CreditoResponse> creditoResponse;
	private List<DespesaResponse> despesaResponse;
	
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

	public List<CreditoResponse> getCreditoResponse() {
		return creditoResponse;
	}

	public void setCreditoResponse(List<CreditoResponse> creditoResponse) {
		this.creditoResponse = creditoResponse;
	}

	public List<DespesaResponse> getDespesaResponse() {
		return despesaResponse;
	}

	public void setDespesaResponse(List<DespesaResponse> despesaResponse) {
		this.despesaResponse = despesaResponse;
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

	public int compareTo(LancamentoResponse o) {
		if (this.getDataCompleta() == null || o.getDataCompleta() == null) {
		      return 0;
		}
		
	    return this.getDataCompleta().compareTo(o.getDataCompleta());
	}
	
}
