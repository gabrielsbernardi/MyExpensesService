package com.br.myexpenses.controle;

import java.util.Date;
import java.util.Calendar;
import java.util.StringJoiner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.br.myexpenses.data.ConexaoDB;
import com.br.myexpenses.model.Credito;
import com.br.myexpenses.model.Despesa;
import com.br.myexpenses.model.Lancamento;
import com.br.myexpenses.utils.Utils;

public class LancamentoControle {

	private EntityManager manager;
	
	public LancamentoControle() {
		manager = ConexaoDB.getEntityManager();
	}
	
	//----------------------------------------------- DESPESA -----------------------------------------------
	public void gerarLancamentos(Despesa d) {
		Double valor = 0.0;
		if (d.getValor() != 0) {
			valor = Utils.duasCasasDecimais(d.getValor() / d.getParcela());
		}
		
		Lancamento l = null;
		Date dataAtual = d.getDataCompra();
		for (int i = 0; i < d.getParcela(); i++) {
			l = new Lancamento();
			l.setDespesa(d.getId());
			l.setUsuario(d.getUsuario());
			l.setValor(valor);
			
			if (i > 0) {
				l.setData(this.getProximoMes(dataAtual));
			} else {
				l.setData(dataAtual);
			}
			
			dataAtual = l.getData();
			
			manager.getTransaction().begin();
			manager.persist(l);
			manager.getTransaction().commit();
		}
		
		
	}

	public void atualizarLancamentos(Despesa d) {
		this.excluirLancamento(d);
		this.gerarLancamentos(d);
	}
	
	public void excluirLancamento(Despesa d) {
		StringJoiner sql = new StringJoiner("\n");
		sql
		.add(" DELETE FROM lancamento l		 ")
		.add(" WHERE l.despesa = :pIdDespesa ");
		
		Query query = this.manager.createNativeQuery(sql.toString());
		query.setParameter("pIdDespesa", d.getId());
		
		this.manager.getTransaction().begin();
		query.executeUpdate();
		this.manager.getTransaction().commit();
	}
	
	//----------------------------------------------- CRÉDITO -----------------------------------------------
	
	public void gerarLancamentos(Credito c) {
		Double valor = 0.0;
		if (c.getValor() != 0) {
			valor = Utils.duasCasasDecimais(c.getValor() / c.getParcela());
		}
		
		Lancamento l = null;
		Date dataAtual = c.getData();
		for (int i = 0; i < c.getParcela(); i++) {
			l = new Lancamento();
			l.setCredito(c.getId());
			l.setUsuario(c.getUsuario());
			l.setValor(valor);
			
			if (i > 0) {
				l.setData(this.getProximoMes(dataAtual));
			} else {
				l.setData(dataAtual);
			}
			
			manager.getTransaction().begin();
			manager.persist(l);
			manager.getTransaction().commit();
			
			dataAtual = l.getData();
		}
	}

	public void atualizarLancamentos(Credito c) {
		this.excluirLancamento(c);
		this.gerarLancamentos(c);
	}
	
	public void excluirLancamento(Credito c) {
		StringJoiner sql = new StringJoiner("\n");
		sql
		.add(" DELETE FROM lancamento l		 ")
		.add(" WHERE l.credito = :pIdCredito ");
		
		Query query = this.manager.createNativeQuery(sql.toString());
		query.setParameter("pIdCredito", c.getId());

		this.manager.getTransaction().begin();
		query.executeUpdate();
		this.manager.getTransaction().commit();
	}
	
	private Date getProximoMes(Date dataCompra) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataCompra);
        cal.add (Calendar.MONTH, 1);
		return cal.getTime();
	}
}
