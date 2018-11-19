package com.br.myexpenses.ws.rest.response;

public class CategoriaResponse extends DefaultResponse {
	
	private String tipoCategoria;
	private String descricao;
	
	public String getTipoCategoria() {
		return tipoCategoria;
	}
	
	public void setTipoCategoria(String tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
