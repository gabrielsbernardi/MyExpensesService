package com.br.myexpenses.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="lancamento")
public class Lancamento {

	@Column(name="usuario")	
	private Long usuario;
	
	@Id
	@Column(name="id_lancamento")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idLancamento;
	
	@Column(name="credito")
	private Long credito;
	
	@Column(name="despesa")
	private Long despesa;
	
	@Column(name="valor")
	private double valor;
	
	@Column(name="data")
	private Date data;

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	public Long getIdLancamento() {
		return idLancamento;
	}

	public void setIdLancamento(Long idLancamento) {
		this.idLancamento = idLancamento;
	}

	public Long getCredito() {
		return credito;
	}

	public void setCredito(Long credito) {
		this.credito = credito;
	}

	public Long getDespesa() {
		return despesa;
	}

	public void setDespesa(Long despesa) {
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
