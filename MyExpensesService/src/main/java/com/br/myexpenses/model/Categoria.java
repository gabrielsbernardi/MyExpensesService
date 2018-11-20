package com.br.myexpenses.model;

import javax.persistence.*;

@Entity
@IdClass(PK_Categoria.class)
@Table(name="categoria")
public class Categoria {

	@Id	
	@Column(name="usuario")
	private int usuario;
	@Id
	@Column(name="id")
	private int id;
	@Column(name="tipo")
	private String tipo;
	@Column(name="descricao")
	private String descricao;
		
	
	public Categoria() {
		
	}
	
	public Categoria(int usuario, int id, String tipo, String descricao) {
		this.usuario = usuario;
		this.id = id;
		this.tipo = tipo;
		this.descricao = descricao;
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

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
