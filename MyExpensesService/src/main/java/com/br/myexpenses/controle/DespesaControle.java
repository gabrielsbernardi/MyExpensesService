package com.br.myexpenses.controle;

import javax.persistence.*;

import com.br.myexpenses.data.ConexaoDB;
import com.br.myexpenses.model.Despesa;

public class DespesaControle {

	private EntityManager manager;
	
	public DespesaControle() {
		manager = ConexaoDB.getEntityManager();
	}
	
	public boolean inserir(Despesa despesa) {
		if (despesa != null) {
			manager.getTransaction().begin();
			manager.persist(despesa);
			manager.getTransaction().commit();			
			return true;
		}
		return false;
	}
	
	public boolean atualizar(Despesa despesa, int id) throws Exception {
		if (despesa != null) {
			Despesa d = buscar(id);
			d.setParcela(despesa.getParcela());
			d.setLocalCompra(despesa.getLocalCompra());
			d.setDescricao(despesa.getDescricao());
			d.setDataCompra(despesa.getDataCompra());			
			d.setCategoria(despesa.getCategoria());
			d.setValor(despesa.getValor());
			manager.getTransaction().begin();
			manager.merge(d);
			manager.getTransaction().commit();
			return true;
		}
		return false;
	}
	
	public boolean excluir(int id) throws Exception {
		Despesa despesa = buscar(id);
		if (despesa != null) {
			manager.getTransaction().begin();
			manager.remove(despesa);
			manager.getTransaction().commit();
			return true;
		}
		return false;
	}
	
	public Despesa buscar(int id) {
		return manager.find(Despesa.class, id);
	}
	
	
}
