package com.br.myexpenses.controle;

import java.util.Date;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.br.myexpenses.data.ConexaoDB;
import com.br.myexpenses.model.Credito;
import com.br.myexpenses.ws.rest.request.CreditoRequest;
import com.br.myexpenses.ws.rest.response.CreditoResponse;

public class CreditoControle {

	private EntityManager manager;
	
	public CreditoControle() {
		manager = ConexaoDB.getEntityManager();
	}
	
	public CreditoResponse inserirCredito(CreditoRequest request) {
		CreditoResponse cr = new CreditoResponse();
		
		Credito c = new Credito();
		c.setUsuario(request.getIdUsuario());
		c.setParcela(request.getParcela());
		c.setDescricao(request.getDescricao());
		c.setData(request.getData());
		c.setValor(request.getValor());
		
		manager.getTransaction().begin();
		manager.persist(c);
		manager.getTransaction().commit();		
		
		LancamentoControle lc = new LancamentoControle();
		lc.gerarLancamentos(c);
		
		return cr;			
	}
	
	public CreditoResponse atualizar(CreditoRequest request) throws Exception {
		CreditoResponse cr = new CreditoResponse();
		
		Credito c = this.manager.find(Credito.class, request.getId());
		Integer parcela = c.getParcela();
		Date data = c.getData();
		Double valor = c.getValor();
		
		try {
			c.setParcela(request.getParcela());
			c.setDescricao(request.getDescricao());
			c.setData(request.getData());			
			c.setValor(request.getValor());
			
			manager.getTransaction().begin();
			manager.merge(c);
			manager.getTransaction().commit();
		} catch (Exception e) {
			cr.setIsException(Boolean.TRUE);
			cr.setMessage(e.getMessage());
		}
		
		if (parcela != c.getParcela() || data != c.getData() || valor != c.getValor()) {
			LancamentoControle lc = new LancamentoControle();
			lc.atualizarLancamentos(c);
		}
		
		return cr;
	}
	
	public CreditoResponse excluirCredito(Long id) throws Exception {
		CreditoResponse cr = new CreditoResponse();
		try {
			Credito c = this.manager.find(Credito.class, id);
			
			LancamentoControle lc = new LancamentoControle();
			lc.excluirLancamento(c);
			
			manager.getTransaction().begin();
			manager.remove(c);
			manager.getTransaction().commit();
		} catch (Exception e) {
			cr.setIsException(Boolean.TRUE);
			cr.setMessage(e.getMessage());
		}
		
		return cr;
	}
	
	public List<CreditoResponse> getCreditos(Long idUsuario){
		StringJoiner sql = new StringJoiner("\n");
		sql
		.add(" SELECT c.id,  		   		 ")
		.add("        c.parcela,       		 ")
		.add("        c.descricao,     		 ")
		.add("        c.valor,  	   		 ")
		.add("        c.data_credito   		 ")
		.add(" FROM credito c          		 ")
		.add(" WHERE c.usuario = :pIdUsuario ")
		.add(" ORDER BY c.descricao 		 ");
		
		Query query = this.manager.createNativeQuery(sql.toString());
		query.setParameter("pIdUsuario", idUsuario);
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		CreditoResponse c = null;
		List<CreditoResponse> list = new ArrayList<CreditoResponse>();
		for (Object[] o : results) {
			c = new CreditoResponse();
			c.setId(((Integer) o[0]).longValue());
			c.setNumParcela((Integer) o[1]);
			c.setDescricao((String) o[2]);
			c.setValor(((BigDecimal) o[3]).doubleValue());
			c.setData((Date) o[4]);
			
			list.add(c);
		}
		return list;
	}
	
}
