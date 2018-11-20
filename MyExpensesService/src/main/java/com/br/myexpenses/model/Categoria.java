package com.br.myexpenses.model;

import javax.persistence.*;

@Entity
@Table(name="categoria")
public class Categoria {

	@Column(name="usuario")
	@ManyToOne(targetEntity = Usuario.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Usuario usuario;
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="tipo")
	private String tipo;
	
	@Column(name="descricao")
	private String descricao;
		
	
	public Categoria() {
		
	}

	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
