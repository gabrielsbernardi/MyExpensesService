package com.br.myexpenses.ws.rest.response;

public class GraficoPizzaResponse extends DefaultResponse {
	
	private String categoria;
	private Double valor;
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
}
