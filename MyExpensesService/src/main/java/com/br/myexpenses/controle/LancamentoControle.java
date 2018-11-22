package com.br.myexpenses.controle;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.StringJoiner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.br.myexpenses.data.ConexaoDB;
import com.br.myexpenses.model.Credito;
import com.br.myexpenses.model.Despesa;
import com.br.myexpenses.model.Lancamento;
import com.br.myexpenses.to.TOLancamentoCredito;
import com.br.myexpenses.to.TOLancamentoDespesa;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.br.myexpenses.utils.Utils;
import com.br.myexpenses.ws.rest.request.LancamentoRequest;
import com.br.myexpenses.ws.rest.response.CreditoResponse;
import com.br.myexpenses.ws.rest.response.DespesaResponse;
import com.br.myexpenses.ws.rest.response.LancamentoResponse;

public class LancamentoControle {

	private EntityManager manager;
	
	public LancamentoControle() {
		manager = ConexaoDB.getEntityManager();
	}
	
	public List<LancamentoResponse> getLancamentos(LancamentoRequest request) {
		List<LancamentoResponse> listReponse = new ArrayList<LancamentoResponse>();
		Map<String, TOLancamentoCredito> mapCreditos = this.getLancamentosCredito(request.getIdUsuario());
		Map<String, TOLancamentoDespesa> mapDespesas = this.getLancamentosDespesas(request.getIdUsuario());

		Boolean percorrerCreditos = mapCreditos.size() <= mapDespesas.size();
		LancamentoResponse lr = null;
		if (percorrerCreditos) {
			for (Map.Entry<String, TOLancamentoCredito> to : mapCreditos.entrySet()) {
			    if (mapDespesas.containsKey(to.getKey())) {
			    	TOLancamentoDespesa toDespesa = mapDespesas.get(to.getKey());
			    	
			    	lr = new LancamentoResponse();
			    	lr.setData(to.getValue().getData());
			    	lr.setDataCompleta(this.getDateCompleta(lr.getData()));
			    	lr.setValor(Utils.duasCasasDecimais(to.getValue().getValor() - toDespesa.getValor()));
			    	lr.setTotalCredito(to.getValue().getValor());
			    	lr.setTotalDespesa(toDespesa.getValor());
			    	listReponse.add(lr);
			    	
			    	mapDespesas.remove(to.getKey());
			    } else {
			    	lr = new LancamentoResponse();
			    	lr.setData(to.getValue().getData());
			    	lr.setDataCompleta(this.getDateCompleta(lr.getData()));
			    	lr.setValor(Utils.duasCasasDecimais(to.getValue().getValor()));
			    	lr.setTotalCredito(to.getValue().getValor());
			    	lr.setTotalDespesa(0.00);
			    	listReponse.add(lr);
			    }
			}
		} else {
			for (Map.Entry<String, TOLancamentoDespesa> to : mapDespesas.entrySet()) {
			    if (mapCreditos.containsKey(to.getKey())) {
			    	TOLancamentoCredito toCredito = mapCreditos.get(to.getKey());
			    	
			    	lr = new LancamentoResponse();
			    	lr.setData(to.getValue().getData());
			    	lr.setDataCompleta(this.getDateCompleta(lr.getData()));
			    	lr.setValor(Utils.duasCasasDecimais(toCredito.getValor() - to.getValue().getValor()));
			    	lr.setTotalCredito(toCredito.getValor());
			    	lr.setTotalDespesa(to.getValue().getValor());
			    	listReponse.add(lr);
			    	
			    	mapCreditos.remove(to.getKey());
			    } else {
			    	lr = new LancamentoResponse();
			    	lr.setData(to.getValue().getData());
			    	lr.setDataCompleta(this.getDateCompleta(lr.getData()));
			    	lr.setValor(Utils.duasCasasDecimais(0.0 - to.getValue().getValor()));
			    	lr.setTotalCredito(0.00);
			    	lr.setTotalDespesa(to.getValue().getValor());
			    	listReponse.add(lr);
			    }
			}
		}
		
		if (percorrerCreditos) {
			if (!mapDespesas.isEmpty()) {
				for (Map.Entry<String, TOLancamentoDespesa> to : mapDespesas.entrySet()) {
					lr = new LancamentoResponse();
			    	lr.setData(to.getValue().getData());
			    	lr.setDataCompleta(this.getDateCompleta(lr.getData()));
			    	lr.setValor(Utils.duasCasasDecimais(0.0 - to.getValue().getValor()));
			    	lr.setTotalCredito(0.00);
			    	lr.setTotalDespesa(to.getValue().getValor());
			    	listReponse.add(lr);
				}
			}
		} else {
			if (!mapCreditos.isEmpty()) {
				for (Map.Entry<String, TOLancamentoCredito> to : mapCreditos.entrySet()) {
					lr = new LancamentoResponse();
			    	lr.setData(to.getValue().getData());
			    	lr.setDataCompleta(this.getDateCompleta(lr.getData()));
			    	lr.setValor(Utils.duasCasasDecimais(to.getValue().getValor()));
			    	lr.setTotalCredito(to.getValue().getValor());
			    	lr.setTotalDespesa(0.00);
			    	listReponse.add(lr);
				}
			}
		}
		
		if (!Utils.listEmpty(listReponse)) {
			Collections.sort(listReponse);
		}
		
		return listReponse;
	}
	
	private Date getDateCompleta(String data) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse("01/" + data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Map<String, TOLancamentoCredito> getLancamentosCredito(Long idUsuario) {
		StringJoiner sql = new StringJoiner("\n");
		
		sql
		.add(" SELECT TO_CHAR(l.data, 'MM/yyyy') AS dat, ")
		.add(" 	   	  SUM(l.valor) AS valor              ")
		.add(" FROM lancamento l                         ")
		.add(" WHERE l.usuario = :pIdUsuario             ")
		.add(" AND l.despesa IS NULL                     ")
		.add(" GROUP BY dat                              ")
		.add(" ORDER BY dat                              ");
		
		Query query = this.manager.createNativeQuery(sql.toString());
		query.setParameter("pIdUsuario", idUsuario);
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		TOLancamentoCredito to = null;
		Map<String, TOLancamentoCredito> mapCreditos = new HashMap<String, TOLancamentoCredito>();
		for (Object[] o : results) {
			to = new TOLancamentoCredito();
			to.setData((String) o[0]);
			to.setValor(((BigDecimal) o[1]).doubleValue());
			
			mapCreditos.put(to.getData(), to);
		}
		return mapCreditos;
	}
	
	private Map<String, TOLancamentoDespesa> getLancamentosDespesas(Long idUsuario) {
		StringJoiner sql = new StringJoiner("\n");
		
		sql
		.add(" SELECT TO_CHAR(l.data, 'MM/yyyy') AS dat, ")
		.add(" 	   	  SUM(l.valor) AS valor              ")
		.add(" FROM lancamento l                         ")
		.add(" WHERE l.usuario = :pIdUsuario             ")
		.add(" AND l.credito IS NULL                     ")
		.add(" GROUP BY dat                              ")
		.add(" ORDER BY dat                              ");
		
		Query query = this.manager.createNativeQuery(sql.toString());
		query.setParameter("pIdUsuario", idUsuario);
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		TOLancamentoDespesa to = null;
		Map<String, TOLancamentoDespesa> mapDespesas = new HashMap<String, TOLancamentoDespesa>();
		for (Object[] o : results) {
			to = new TOLancamentoDespesa();
			to.setData((String) o[0]);
			to.setValor(((BigDecimal) o[1]).doubleValue());
			
			mapDespesas.put(to.getData(), to);
		}
		return mapDespesas;
	}
	
	public LancamentoResponse getCreditosDespesasLancamentoByData(String data) {
		return null;
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
		this.gerarLancamentos(d);
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
	
	//----------------------------------------------- CRÉDITO -----------------------------------------------
	
	public void gerarLancamentos(Credito c) {
		Double valor = 0.0;
		if (c.getValor() != 0) {
			valor = Utils.duasCasasDecimais(c.getValor() / c.getParcela());
		}
		
		Lancamento l = null;
		Date dataAtual = c.getData();
		for (int i = 0; i < c.getParcela(); i++) {
			l = new Lancamento();
			l.setCredito(c.getId());
			l.setUsuario(c.getUsuario());
			l.setValor(valor);
			
			if (i > 0) {
				l.setData(this.getProximoMes(dataAtual));
			} else {
				l.setData(dataAtual);
			}
			
			manager.getTransaction().begin();
			manager.persist(l);
			manager.getTransaction().commit();
			
			dataAtual = l.getData();
		}
	}

	public void atualizarLancamentos(Credito c) {
		this.excluirLancamento(c);
		this.gerarLancamentos(c);
	}
	
	public void excluirLancamento(Credito c) {
		StringJoiner sql = new StringJoiner("\n");
		sql
		.add(" DELETE FROM lancamento l		 ")
		.add(" WHERE l.credito = :pIdCredito ");
		
		Query query = this.manager.createNativeQuery(sql.toString());
		query.setParameter("pIdCredito", c.getId());

		this.manager.getTransaction().begin();
		query.executeUpdate();
		this.manager.getTransaction().commit();
	}
	
	private Date getProximoMes(Date dataCompra) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataCompra);
        cal.add (Calendar.MONTH, 1);
		return cal.getTime();
	}

	public LancamentoResponse getCreditosDespesaLancamento(LancamentoRequest request) {
		LancamentoResponse lr = new LancamentoResponse();
		lr.setData(request.getData());
		lr.setValor(request.getValor());
		lr.setTotalCredito(request.getTotalCredito());
		lr.setTotalDespesa(request.getTotalDespesa());
		lr.setCreditoResponse(this.gerCreditosByDateLancamento(request));
		lr.setDespesaResponse(this.gerDespesasByDateLancamento(request));
		
		return lr;
	}
	
	private List<CreditoResponse> gerCreditosByDateLancamento(LancamentoRequest request) {
		StringJoiner sql = new StringJoiner("\n");
		sql
		.add("  SELECT l.credito FROM lancamento l			")
		.add("  WHERE l.usuario = :pIdUsuario               ")
		.add("  AND l.despesa IS NULL                       ")
		.add("  AND to_char(l.data, 'MM/yyyy') = :pDate     ");
		
		Query query = this.manager.createNativeQuery(sql.toString());
		query.setParameter("pIdUsuario", request.getIdUsuario());
		query.setParameter("pDate", request.getData());
		
		@SuppressWarnings("unchecked")
		List<Object> results = query.getResultList();
		
		CreditoResponse c = null;
		List<CreditoResponse> list = new ArrayList<CreditoResponse>();
		for (Object idCredito : results) {
			Credito credito = this.manager.find(Credito.class, ((Integer) idCredito).longValue());
			
			c = new CreditoResponse();
			c.setDescricao(credito.getDescricao());
			c.setValor(Utils.duasCasasDecimais(credito.getValor()));
			c.setData(credito.getData());
			c.setNumParcela((Integer) credito.getParcela());
			
			list.add(c);
		}
		
		if (Utils.listEmpty(list)) {
			Collections.sort(list);
		}
		
		return list;
	}
	
	private List<DespesaResponse> gerDespesasByDateLancamento(LancamentoRequest request) {
		StringJoiner sql = new StringJoiner("\n");
		sql
		.add("  SELECT l.despesa FROM lancamento l			")
		.add("  WHERE l.usuario = :pIdUsuario               ")
		.add("  AND l.credito IS NULL                       ")
		.add("  AND to_char(l.data, 'MM/yyyy') = :pDate     ");
		
		Query query = this.manager.createNativeQuery(sql.toString());
		query.setParameter("pIdUsuario", request.getIdUsuario());
		query.setParameter("pDate", request.getData());
		
		@SuppressWarnings("unchecked")
		List<Object> results = query.getResultList();
		
		DespesaResponse c = null;
		List<DespesaResponse> list = new ArrayList<DespesaResponse>();
		for (Object idDespesa : results) {
			Despesa despesa = this.manager.find(Despesa.class, ((Integer) idDespesa).longValue());
			
			c = new DespesaResponse();
			c.setDescricao(despesa.getDescricao());
			c.setValor(Utils.duasCasasDecimais(despesa.getValor()));
			c.setData(despesa.getDataCompra());
			c.setNumParcela((Integer) despesa.getParcela());
			
			list.add(c);
		}
		
		if (Utils.listEmpty(list)) {
			Collections.sort(list);
		}
		
		return list;
	}
}
