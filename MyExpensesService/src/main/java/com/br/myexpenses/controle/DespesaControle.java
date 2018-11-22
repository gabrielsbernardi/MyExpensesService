package com.br.myexpenses.controle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

import javax.persistence.*;

import com.br.myexpenses.data.ConexaoDB;
import com.br.myexpenses.model.Despesa;
import com.br.myexpenses.utils.Utils;
import com.br.myexpenses.ws.rest.request.DespesaRequest;
import com.br.myexpenses.ws.rest.response.DespesaCategoriaResponse;
import com.br.myexpenses.ws.rest.response.DespesaResponse;

public class DespesaControle {

	private EntityManager manager;
	
	public DespesaControle() {
		manager = ConexaoDB.getEntityManager();
	}
	
	public DespesaResponse inserirDespesa(DespesaRequest request) {
		DespesaResponse dr = new DespesaResponse();
		
		Despesa d = new Despesa();
		d.setUsuario(request.getIdUsuario());
		d.setParcela(request.getParcela());
		d.setLocalCompra(request.getLocalCompra());
		d.setDescricao(request.getDescricao());
		d.setDataCompra(request.getDataCompra());
		d.setCategoria(request.getCategoria());
		d.setValor(request.getValor());
		
		manager.getTransaction().begin();
		manager.persist(d);
		manager.getTransaction().commit();		
		
		LancamentoControle lc = new LancamentoControle();
		lc.gerarLancamentos(d);
		
		return dr;			
	}
	
	public DespesaResponse atualizar(DespesaRequest request) throws Exception {
		DespesaResponse dr = new DespesaResponse();
		
		Despesa d = this.manager.find(Despesa.class, request.getId());
		Integer parcela = d.getParcela();
		Date data = d.getDataCompra();
		Double valor = d.getValor();
		
		try {
			d.setParcela(request.getParcela());
			d.setLocalCompra(request.getLocalCompra());
			d.setDescricao(request.getDescricao());
			d.setDataCompra(request.getDataCompra());			
			d.setCategoria(request.getCategoria());
			d.setValor(request.getValor());
			
			manager.getTransaction().begin();
			manager.merge(d);
			manager.getTransaction().commit();
		} catch (Exception e) {
			dr.setIsException(Boolean.TRUE);
			dr.setMessage(e.getMessage());
		}
		
		if (parcela != d.getParcela() || data != d.getDataCompra() || valor != d.getValor()) {
			LancamentoControle lc = new LancamentoControle();
			lc.atualizarLancamentos(d);
		}
		
		return dr;
	}
	
	public DespesaResponse excluirDespesa(Long id) throws Exception {
		DespesaResponse dr = new DespesaResponse();
		try {
			Despesa d = this.manager.find(Despesa.class, id);
			
			LancamentoControle lc = new LancamentoControle();
			lc.excluirLancamento(d);
			
			manager.getTransaction().begin();
			manager.remove(d);
			manager.getTransaction().commit();
		} catch (Exception e) {
			dr.setIsException(Boolean.TRUE);
			dr.setMessage(e.getMessage());
		}
		
		return dr;
	}
	
	public List<DespesaResponse> getDespesas(DespesaRequest request) {
		StringJoiner sql = new StringJoiner("\n");
		sql
		.add(" SELECT d.id,       			   				  ")
		.add("        d.descricao,    			      		  ")
		.add("        d.data_compra,    			      	  ")
		.add("        d.valor,    			      			  ")
		.add("        d.parcela,    			      		  ")
		.add("        d.local_compra,    			      	  ")
		.add("        c.id AS id_categoria,    			      ")
		.add("        c.tipo AS tipo_categoria 				  ")
		.add(" FROM despesa d   			   				  ")
		.add(" INNER JOIN categoria c ON (c.id = d.categoria) ")
		.add(" WHERE d.usuario = :pIdUsuario   				  ");
		
		if (Utils.booleanIsTrue(request.getIsSearch())) {
			if (!Utils.stringIsNull(request.getDescricao())) {
				sql.add(Utils.sqlAndEqualsPesquisaString("d.descricao", request.getDescricao()));
			}
			
			if (request.getParcela() != null) {
				sql.add(Utils.sqlAndEqualsPesquisaInteger("d.parcela", request.getParcela()));
			}
			
			if (!Utils.stringIsNull(request.getLocalCompra())) {
				sql.add(Utils.sqlAndEqualsPesquisaString("d.local_compra", request.getLocalCompra()));
			}
		}
		
		sql.add(" ORDER BY d.descricao ");
		
		Query query = this.manager.createNativeQuery(sql.toString());
		query.setParameter("pIdUsuario", request.getIdUsuario());
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		DespesaResponse d = null;
		List<DespesaResponse> list = new ArrayList<DespesaResponse>();
		for (Object[] o : results) {
			d = new DespesaResponse();
			d.setId(((Integer) o[0]).longValue());
			d.setDescricao((String) o[1]);
			d.setData((Date) o[2]);
			d.setValor(((BigDecimal) o[3]).doubleValue());
			d.setNumParcela((Integer) o[4]);
			d.setLocal((String) o[5]);
			
			DespesaCategoriaResponse dcr = new DespesaCategoriaResponse();
			dcr.setId(((Integer) o[6]).longValue());
			dcr.setTipoCategoria((String) o[7]);
			d.setCategoria(dcr);
			
			list.add(d);
		}
		
		return list;
	}

	public List<DespesaCategoriaResponse> getDespesasCategorias(Long idUsuario) {
		StringJoiner sql = new StringJoiner("\n");
		sql
		.add(" SELECT c.id,       			 ")
		.add("        c.tipo      			 ")
		.add(" FROM categoria c   			 ")
		.add(" WHERE c.usuario = :pIdUsuario ")
		.add(" ORDER BY c.tipo				 ");
		
		Query query = this.manager.createNativeQuery(sql.toString());
		query.setParameter("pIdUsuario", idUsuario);
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		DespesaCategoriaResponse dcr = null;
		List<DespesaCategoriaResponse> list = new ArrayList<DespesaCategoriaResponse>();
		for (Object[] o : results) {
			dcr = new DespesaCategoriaResponse();
			dcr.setId(((Integer) o[0]).longValue());
			dcr.setTipoCategoria((String) o[1]);
			list.add(dcr);
		}
		
		return list;
	}
	
	
}
