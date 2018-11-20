package com.br.myexpenses.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="credito")
public class Credito {
	
	
	@Column(name="usuario")
	private Integer usuario;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	
	@Column(name="numParcelas")
	private Integer numParcelas;
	
	@Column(name="descricao", length=150)
	private String descricao;
	
	@Column(name="valor")
	private Double valor;
	
	@Column(name="data")
	private Date data;
	
	
	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public Integer getNumParcelas() {
		return numParcelas;
	}
	
	public void setNumParcelas(Integer numParcelas) {
		this.numParcelas = numParcelas;
	}
	
}
