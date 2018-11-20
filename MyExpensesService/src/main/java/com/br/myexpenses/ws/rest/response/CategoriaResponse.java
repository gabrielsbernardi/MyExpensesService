package com.br.myexpenses.ws.rest.response;

public class CategoriaResponse extends DefaultResponse {
	
	private String tipoCategoria;
	private String descricao;
	private Boolean categoriaExistente;
	private Boolean possuiDespesa;
	
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

	public Boolean getCategoriaExistente() {
		return categoriaExistente;
	}

	public void setCategoriaExistente(Boolean categoriaExistente) {
		this.categoriaExistente = categoriaExistente;
	}

	public Boolean getPossuiDespesa() {
		return possuiDespesa;
	}

	public void setPossuiDespesa(Boolean possuiDespesa) {
		this.possuiDespesa = possuiDespesa;
	}
	
}
