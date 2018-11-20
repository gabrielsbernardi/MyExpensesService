package com.br.myexpenses.controle;

import java.sql.Date;
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
	
	public CreditoResponse inserir(CreditoRequest request) {
		CreditoResponse crr = new CreditoResponse();
		Credito c = new Credito();
		c.setUsuario(request.getUsuario());
		c.setDescricao(request.getDescricao());
		//c.setParcelas(request.getNumParcelas());
		c.setData(request.getData());
		c.setValor(request.getValor());
		manager.getTransaction().begin();
		manager.persist(c);
		manager.getTransaction().commit();
		return crr;
	}
	
	public void atualizarCredito(CreditoRequest request) throws Exception {		
			Credito c = new Credito();
			c.setId(request.getId());
			c.setUsuario(request.getUsuario());
			c.setDescricao(request.getDescricao());
			//c.setNumParcelas(request.getNumParcelas());
			c.setData(request.getData());
			c.setValor(request.getValor());
			
			manager.getTransaction().begin();
			manager.merge(c);
			manager.getTransaction().commit();
		
	}
	
	public void excluirCredito(long id) throws Exception {
		Credito credito = this.manager.find(Credito.class, id);		
		manager.getTransaction().begin();
		manager.remove(credito);
		manager.getTransaction().commit();		
	}
	
	public List<CreditoResponse> getCreditos(Long idUsuario){
		StringJoiner sql = new StringJoiner("\n");
		sql
		.add(" SELECT C.ID,  		   ")
		.add("        C.PARCELA,       ")
		.add("        C.DESCRICAO,     ")
		.add("        C.VALOR,  	   ")
		.add("        C.DATA_CREDITO,  ")
		.add(" FROM CREDITO C          ")
		.add(" WHERE C.USUARIO = :pIdUsuario");
		
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
			c.setValor((Double) o[3]);
			c.setData((Date) o[4]);
		}
		return list;
	}
	
	
	public Credito buscar(int id) {
		return manager.find(Credito.class, id);
	}
	
}
