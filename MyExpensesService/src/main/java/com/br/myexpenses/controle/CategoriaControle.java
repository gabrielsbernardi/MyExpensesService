package com.br.myexpenses.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import javax.persistence.*;

import com.br.myexpenses.data.ConexaoDB;
import com.br.myexpenses.model.Categoria;
import com.br.myexpenses.utils.Utils;
import com.br.myexpenses.ws.rest.request.CategoriaRequest;
import com.br.myexpenses.ws.rest.response.CategoriaResponse;

public class CategoriaControle {
	
	private EntityManager manager;

	public CategoriaControle() {
		manager = ConexaoDB.getEntityManager();
	}

	public CategoriaResponse inserirCategoria(CategoriaRequest request) {
		CategoriaResponse cr = new CategoriaResponse();
		if (this.getCategoriaExistente(request.getTipoCategoria())) {
			Categoria c = new Categoria();
			c.setTipo(request.getTipoCategoria());
			c.setDescricao(request.getDescricao());
			c.setUsuario(request.getIdUsuario());
			
			manager.getTransaction().begin();
			manager.persist(c);
			manager.getTransaction().commit();			
		} else {
			cr.setCategoriaExistente(Boolean.TRUE);
		}
		
		return cr;
	}

	public CategoriaResponse atualizarCategoria(CategoriaRequest request) {
		CategoriaResponse cr = new CategoriaResponse();
		try {
			Categoria c = new Categoria();
			c.setId(request.getId());
			c.setTipo(request.getTipoCategoria());
			c.setDescricao(request.getDescricao());
			c.setUsuario(request.getIdUsuario());
			
			manager.getTransaction().begin();
			manager.merge(c);
			manager.getTransaction().commit();
		} catch (Exception e) {
			cr.setIsException(Boolean.TRUE);
			cr.setMessage(e.getMessage());
		}
		return cr;
	}

	public CategoriaResponse excluirCategoria(Long id) {
		CategoriaResponse cr = new CategoriaResponse();
		try {
			if (this.possuiDespesa(id)) {
				cr.setPossuiDespesa(Boolean.TRUE);
			} else {
				Categoria categoria = this.manager.find(Categoria.class, id);
				manager.getTransaction().begin();
				manager.remove(categoria);
				manager.getTransaction().commit();
			}
		} catch (Exception e) {
			cr.setIsException(Boolean.TRUE);
			cr.setMessage(e.getMessage());
		}
		return cr;
	}

	private Boolean possuiDespesa(Long idCategoria) {
		StringJoiner sql = new StringJoiner("\n");
		sql
		.add(" SELECT 1 	         	 		 ")
		.add(" FROM despesa d 			 		 ")
		.add(" WHERE d.categoria = :pIdCategoria ");
		
		Query query = this.manager.createNativeQuery(sql.toString());
		query.setParameter("pIdCategoria", idCategoria);
		
		return !Utils.listEmpty(query.getResultList());
	}
	
	public List<CategoriaResponse> getCategorias(CategoriaRequest request) {
		StringJoiner sql = new StringJoiner("\n");
		sql
		.add(" SELECT c.id,       			 ")
		.add("        c.tipo,     			 ")
		.add("        c.descricao 			 ")
		.add(" FROM categoria c   			 ")
		.add(" WHERE c.usuario = :pIdUsuario ");
		
		if (Utils.booleanIsTrue(request.getIsSearch())) {
			if (!Utils.stringIsNull(request.getTipoCategoria())) {
				sql.add(Utils.sqlAndEqualsPesquisaString("c.tipo", request.getTipoCategoria()));
			}
			
			if (!Utils.stringIsNull(request.getDescricao())) {
				sql.add(Utils.sqlAndEqualsPesquisaString("c.descricao", request.getDescricao()));
			}
		}
		
		sql.add(" ORDER BY c.tipo				 ");
		
		Query query = this.manager.createNativeQuery(sql.toString());
		query.setParameter("pIdUsuario", request.getIdUsuario());
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		CategoriaResponse c = null;
		List<CategoriaResponse> list = new ArrayList<CategoriaResponse>();
		for (Object[] o : results) {
			c = new CategoriaResponse();
			c.setId(((Integer) o[0]).longValue());
			c.setTipoCategoria((String) o[1]);
			c.setDescricao((String) o[2]);
			list.add(c);
		}
		
		return list;
	}
	
	private Boolean getCategoriaExistente(String tipo) {
		StringJoiner sql = new StringJoiner("\n");
		sql
		.add(" SELECT 1 	         	 ")
		.add(" FROM categoria c 			 ")
		.add(" WHERE UPPER(c.tipo) = :pTipo ");
		
		Query query = this.manager.createNativeQuery(sql.toString());
		query.setParameter("pTipo", tipo.toUpperCase());
		
		return Utils.listEmpty(query.getResultList());
	}
}
