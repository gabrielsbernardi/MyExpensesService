package com.br.myexpenses.ws.rest.response;

public class GeralResponse {
	
	private String mes;
	private Double valorCreditos;
	private Double valorDespesas;
	private Double valorFinal;
	
	public String getMes() {
		return mes;
	}
	
	public void setMes(String mes) {
		this.mes = mes;
	}
	
	public Double getValorCreditos() {
		return valorCreditos;
	}
	
	public void setValorCreditos(Double valorCreditos) {
		this.valorCreditos = valorCreditos;
	}
	
	public Double getValorDespesas() {
		return valorDespesas;
	}
	
	public void setValorDespesas(Double valorDespesas) {
		this.valorDespesas = valorDespesas;
	}
	
	public Double getValorFinal() {
		return valorFinal;
	}
	
	public void setValorFinal(Double valorFinal) {
		this.valorFinal = valorFinal;
	}
	
}
