package com.br.myexpenses.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import javax.persistence.*;

import com.br.myexpenses.data.ConexaoDB;
import com.br.myexpenses.model.Categoria;
import com.br.myexpenses.ws.rest.response.CategoriaResponse;

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
	
	public List<CategoriaResponse> getCategorias(Integer idUsuario) {
		StringJoiner sql = new StringJoiner("\n");
		sql
		.add(" SELECT c.id,       			 ")
		.add("        c.tipo,     			 ")
		.add("        c.descricao 			 ")
		.add(" FROM categoria c   			 ")
		.add(" WHERE c.usuario = :pIdUsuario ");
		
		Query query = this.manager.createNativeQuery(sql.toString());
		query.setParameter("pIdUsuario", idUsuario);
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		CategoriaResponse c = null;
		List<CategoriaResponse> list = new ArrayList<CategoriaResponse>();
		for (Object[] o : results) {
			c = new CategoriaResponse();
			c.setId((Integer) o[0]);
			c.setTipoCategoria((String) o[1]);
			c.setDescricao((String) o[2]);
			list.add(c);
		}
		
		return list;
	}
}
