package com.br.myexpenses.model;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name="despesa")
public class Despesa {
	
	
	@Column(name="usuario")	
	private Usuario usuario;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
		
	@Column(name="parcela")
	private Integer parcela;
	
	@Column(name="localCompra", length=150)
	private String localCompra;
	
	@Column(name="descricao", length=150)
	private String descricao;
	
	@Column(name="dataCompra")
	private Date dataCompra;
	
	@Column(name="categoria")
	@ManyToOne(targetEntity = Usuario.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Categoria categoria;
	
	@Column(name="valor")
	private double valor;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getParcela() {
		return parcela;
	}

	public void setParcela(int parcela) {
		this.parcela = parcela;
	}

	public String getLocalCompra() {
		return localCompra;
	}

	public void setLocalCompra(String localCompra) {
		this.localCompra = localCompra;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
	
}
