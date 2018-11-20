package com.br.myexpenses.controle;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
	
	//----------------------------------------------- CR�DITO -----------------------------------------------
	
		public void gerarLancamentos(Credito c) {
			Double valor = 0.0;
			if (c.getValor() != 0) {
				valor = Utils.duasCasasDecimais(c.getValor() / c.getParcela());
			}
			
			List<Lancamento> lancamentos = new ArrayList<Lancamento>();
			Lancamento l = null;
			Date dataAtual = c.getData();
			for (int i = 0; i < c.getParcela(); i++) {
				l = new Lancamento();
				l.setDespesa(c.getId());
				l.setUsuario(c.getUsuario());
				l.setValor(valor);
				
				if (i > 0) {
					l.setData(this.getProximoMes(dataAtual));
				} else {
					l.setData(dataAtual);
				}
				
				lancamentos.add(l);
				dataAtual = l.getData();
			}
			
			manager.getTransaction().begin();
			manager.persist(lancamentos);
			manager.getTransaction().commit();
		}

		public void atualizarLancamentos(Credito c) {
			this.excluirLancamento(c);
		}
		
		public void excluirLancamento(Credito c) {
			StringJoiner sql = new StringJoiner("\n");
			sql
			.add(" DELETE FROM lancamento l		 ")
			.add(" WHERE l.usuario = :pIdUsuario ")
			.add(" AND l.credito = :pIdCredito ");
			
			Query query = this.manager.createNativeQuery(sql.toString());
			query.setParameter("pIdusuario", c.getUsuario());
			query.setParameter("pIdCredito", c.getId());
			query.executeUpdate();
		}
	
	private Date getProximoMes(Date dataCompra) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataCompra);
        cal.add (Calendar.MONTH, 1);
		return cal.getTime();
	}
}
