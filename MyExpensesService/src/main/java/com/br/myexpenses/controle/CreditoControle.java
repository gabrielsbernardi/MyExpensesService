package com.br.myexpenses.controle;

import javax.persistence.EntityManager;

import com.br.myexpenses.data.ConexaoDB;
import com.br.myexpenses.model.Credito;
import com.br.myexpenses.model.Despesa;

public class CreditoControle {

	private EntityManager manager;
	
	public CreditoControle() {
		manager = ConexaoDB.getEntityManager();
	}
	
	public boolean inserir(Credito credito) {
		if (credito != null) {
			manager.getTransaction().begin();
			manager.persist(credito);
			manager.getTransaction().commit();			
			return true;
		}
		return false;
	}
	
	public boolean atualizar(Credito credito, int id) throws Exception {
		if (credito != null) {
			Credito c = buscar(id);			
			c.setDescricao(credito.getDescricao());
			c.setNumParcelas(credito.getNumParcelas());
			c.setData(credito.getData());
			c.setValor(credito.getValor());
			manager.getTransaction().begin();
			manager.merge(c);
			manager.getTransaction().commit();
			return true;
		}
		return false;
	}
	
	public boolean excluir(int id) throws Exception {
		Credito credito = buscar(id);
		if (credito != null) {
			manager.getTransaction().begin();
			manager.remove(credito);
			manager.getTransaction().commit();
			return true;
		}
		return false;
	}
	
	public Credito buscar(int id) {
		return manager.find(Credito.class, id);
	}
	
}
