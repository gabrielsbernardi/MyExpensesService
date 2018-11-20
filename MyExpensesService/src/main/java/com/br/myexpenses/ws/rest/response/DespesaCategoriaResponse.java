package com.br.myexpenses.ws.rest.response;

public class DespesaCategoriaResponse {
	
	private Long id;
	private String tipoCategoria;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTipoCategoria() {
		return tipoCategoria;
	}

	public void setTipoCategoria(String tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}
	
}
