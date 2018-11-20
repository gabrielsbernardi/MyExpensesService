package com.br.myexpenses.controle;

import javax.persistence.EntityManager;

import com.br.myexpenses.data.ConexaoDB;
import com.br.myexpenses.model.Lancamento;

public class LancamentoControle {

	private EntityManager manager;
	
	public LancamentoControle() {
		manager = ConexaoDB.getEntityManager();
	}
	
	public boolean inserir(Lancamento Lancamento) {
		if (Lancamento != null) {
			manager.getTransaction().begin();
			manager.persist(Lancamento);
			manager.getTransaction().commit();			
			return true;
		}
		return false;
	}
	
	
	public boolean atualizar(Lancamento lancamento, int id) throws Exception {
		if (lancamento != null) {
			Lancamento l = buscar(id);			
			l.setCredito(lancamento.getCredito());
			l.setDespesa(lancamento.getDespesa());
			l.setValor(lancamento.getValor());
			l.setData(lancamento.getData());			
			manager.getTransaction().begin();
			manager.merge(l);
			manager.getTransaction().commit();
			return true;
		}
		return false;
	}
	
	public boolean excluir(int id) throws Exception {
		Lancamento lancamento = buscar(id);
		if (lancamento != null) {
			manager.getTransaction().begin();
			manager.remove(lancamento);
			manager.getTransaction().commit();
			return true;
		}
		return false;
	}
	
	public Lancamento buscar(int id) {
		return manager.find(Lancamento.class, id);
	}
	
}
