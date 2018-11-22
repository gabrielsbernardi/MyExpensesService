package com.br.myexpenses.ws.rest.response;

public class GraficoLinhaResponse extends DespesaResponse {

	private String mes;
	private Double valorCredito;
	private Double valorDepesa;
	
	public String getMes() {
		return mes;
	}
	
	public void setMes(String mes) {
		this.mes = mes;
	}
	
	public Double getValorCredito() {
		return valorCredito;
	}
	
	public void setValorCredito(Double valorCredito) {
		this.valorCredito = valorCredito;
	}
	
	public Double getValorDepesa() {
		return valorDepesa;
	}
	
	public void setValorDepesa(Double valorDepesa) {
		this.valorDepesa = valorDepesa;
	}
}
