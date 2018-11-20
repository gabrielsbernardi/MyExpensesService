package com.br.myexpenses.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="lancamento")
public class Lancamento {

	@Column(name="usuario")
	@ManyToOne(targetEntity = Usuario.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Usuario usuario;
	
	@Id
	@Column(name="idlancamento")
	private Integer idLancamento;
	
	@Column(name="credito")
	private Credito credito;
	
	@Column(name="despesa")
	private Despesa despesa;
	
	@Column(name="valor")
	private double valor;
	
	@Column(name="data")
	private Date data;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getIdLancamento() {
		return idLancamento;
	}

	public void setIdLancamento(Integer idLancamento) {
		this.idLancamento = idLancamento;
	}

	public Credito getCredito() {
		return credito;
	}

	public void setCredito(Credito credito) {
		this.credito = credito;
	}

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
	
}
