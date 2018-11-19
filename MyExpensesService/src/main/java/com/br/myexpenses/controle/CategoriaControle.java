package com.br.myexpenses.controle;

import javax.persistence.*;

import com.br.myexpenses.data.ConexaoDB;
import com.br.myexpenses.model.Categoria;

public class CategoriaControle implements DAO<Categoria> {
	private EntityManager manager;

	public CategoriaControle() {
		manager = ConexaoDB.getEntityManager();
	}

	public boolean inserir(Categoria categoria) {
		if (categoria != null) {
			manager.getTransaction().begin();
			manager.persist(categoria);
			manager.getTransaction().commit();			
			return true;
		}
		return false;
	}

	public boolean atualizar(Categoria categoria, int id) throws Exception {
		if (categoria != null) {
			Categoria c = buscar(id);
			c.setTipo(categoria.getTipo());
			manager.getTransaction().begin();
			manager.merge(c);
			manager.getTransaction().commit();
			return true;
		}
		return false;
	}

	public boolean excluir(int id) throws Exception {
		Categoria categoria = buscar(id);
		if (categoria != null) {
			manager.getTransaction().begin();
			manager.remove(categoria);
			manager.getTransaction().commit();
			return true;
		}
		return false;
	}

	public Categoria buscar(int id) {
		return manager.find(Categoria.class, id);
	}

	public java.util.List<Categoria> listar() {
		TypedQuery<Categoria> query = manager.createQuery(
				"select new Categoria(c.categoria) from Categoria c", Categoria.class);
		return query.getResultList();
	}
}
