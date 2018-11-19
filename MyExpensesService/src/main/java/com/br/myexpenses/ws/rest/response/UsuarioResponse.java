package com.br.myexpenses.ws.rest.response;

public class UsuarioResponse extends DefaultResponse {
	
	private Integer id;
	private Boolean usuarioValido;
	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getUsuarioValido() {
		return usuarioValido;
	}

	public void setUsuarioValido(Boolean usuarioValido) {
		this.usuarioValido = usuarioValido;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
