package com.br.myexpenses.ws.rest.request;

public class CategoriaRequest extends DefaultRequest {
	
	private Long id;
	private String tipoCategoria;
	private String descricao;
	private Boolean isSearch;
	
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
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getIsSearch() {
		return isSearch;
	}

	public void setIsSearch(Boolean isSearch) {
		this.isSearch = isSearch;
	}

}
