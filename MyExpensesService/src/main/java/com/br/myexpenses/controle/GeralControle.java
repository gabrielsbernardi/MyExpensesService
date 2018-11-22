package com.br.myexpenses.controle;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.br.myexpenses.data.ConexaoDB;
import com.br.myexpenses.utils.Utils;
import com.br.myexpenses.ws.rest.request.GeralRequest;
import com.br.myexpenses.ws.rest.response.CategoriaResponse;
import com.br.myexpenses.ws.rest.response.GeralResponse;
import com.br.myexpenses.ws.rest.response.GraficoLinhaResponse;
import com.br.myexpenses.ws.rest.response.GraficoPizzaResponse;

public class GeralControle {

	private EntityManager manager;

	public GeralControle() {
		manager = ConexaoDB.getEntityManager();
	}

	public List<GraficoLinhaResponse> getDadosGraficoLinha(GeralRequest request) {
		Date dataAtual = new Date();
		List<GraficoLinhaResponse> dados = new ArrayList<GraficoLinhaResponse>();
		dados.add(this.dadosDataEspecifica(this.getMes(dataAtual, -2), request.getIdUsuario()));
		dados.add(this.dadosDataEspecifica(this.getMes(dataAtual, -1), request.getIdUsuario()));
		dados.add(this.dadosDataEspecifica(dataAtual, request.getIdUsuario()));
		dados.add(this.dadosDataEspecifica(this.getMes(dataAtual, 1), request.getIdUsuario()));
		dados.add(this.dadosDataEspecifica(this.getMes(dataAtual, 2), request.getIdUsuario()));
		return dados;
	}
	
	private GraficoLinhaResponse dadosDataEspecifica(Date data, Long idUsuario) {
		GraficoLinhaResponse response = new GraficoLinhaResponse();
		String mes = new SimpleDateFormat("MM/yyyy").format(data);
		response.setMes(Utils.getMesPorExtenso(mes));
		response.setValorCredito(Utils.duasCasasDecimais(this.getValorCreditoByDateLancamento(idUsuario, mes)));
		response.setValorDepesa(Utils.duasCasasDecimais(this.getValorDespesaByDateLancamento(idUsuario, mes)));
		return response;
	}
	
	private Double getValorCreditoByDateLancamento(Long idUsuario, String data) {
		StringJoiner sql = new StringJoiner("\n");
		
		sql
		.add(" SELECT TO_CHAR(l.data, 'MM/yyyy') AS dat, ")
		.add(" 	   	  SUM(l.valor) AS valor              ")
		.add(" FROM lancamento l                         ")
		.add(" WHERE l.usuario = :pIdUsuario             ")
		.add(" AND l.despesa IS NULL                     ")
		.add(" AND to_char(l.data, 'MM/yyyy') = :pDate   ")
		.add(" GROUP BY dat                              ")
		.add(" ORDER BY dat                              ");
		
		Query query = this.manager.createNativeQuery(sql.toString());
		query.setParameter("pIdUsuario", idUsuario);
		query.setParameter("pDate", data);
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		return Utils.listEmpty(results) ? 0.0 : ((BigDecimal) results.get(0)[1]).doubleValue();
	}
	
	private Double getValorDespesaByDateLancamento(Long idUsuario, String data) {
		StringJoiner sql = new StringJoiner("\n");
		
		sql
		.add(" SELECT TO_CHAR(l.data, 'MM/yyyy') AS dat, ")
		.add(" 	   	  SUM(l.valor) AS valor              ")
		.add(" FROM lancamento l                         ")
		.add(" WHERE l.usuario = :pIdUsuario             ")
		.add(" AND l.credito IS NULL                     ")
		.add(" AND to_char(l.data, 'MM/yyyy') = :pDate   ")
		.add(" GROUP BY dat                              ")
		.add(" ORDER BY dat                              ");
		
		Query query = this.manager.createNativeQuery(sql.toString());
		query.setParameter("pIdUsuario", idUsuario);
		query.setParameter("pDate", data);
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		return Utils.listEmpty(results) ? 0.0 : ((BigDecimal) results.get(0)[1]).doubleValue();
	}
	
	private Date getMes(Date data, Integer mes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
        cal.add (Calendar.MONTH, mes);
		return cal.getTime();
	}

	public List<GraficoPizzaResponse> getDadosGraficoPizza(GeralRequest request) {
		List<GraficoPizzaResponse> dados = new ArrayList<GraficoPizzaResponse>();
		GraficoPizzaResponse response = null;
		for (CategoriaResponse categoria : this.getCategorias(request.getIdUsuario())) {
			Double valor = this.getValorPorCategoriaEData(categoria.getId(), request.getIdUsuario());
			if (valor != null) {
				response = new GraficoPizzaResponse();
				response.setCategoria(categoria.getTipoCategoria());
				response.setValor(Utils.duasCasasDecimais(valor));
				dados.add(response);
			}
		}
		return dados;
	}
	
	private Double getValorPorCategoriaEData(Long idCategoria, Long idUsuario) {
		String data = new SimpleDateFormat("MM/yyyy").format(new Date());
		
		StringJoiner sql = new StringJoiner("\n");
		sql
		.add(" SELECT TO_CHAR(l.data, 'MM/yyyy') AS dat,      ")
		.add(" 	   	  SUM(l.valor) AS valor                   ")
		.add(" FROM lancamento l                              ")
		.add(" INNER JOIN despesa d ON (d.id = l.despesa)     ")
		.add(" INNER JOIN categoria c ON (c.id = d.categoria) ")
		.add(" WHERE l.usuario = :pIdUsuario                  ")
		.add(" AND l.despesa IS NOT NULL                      ")
		.add(" AND to_char(l.data, 'MM/yyyy') = :pDate        ")
		.add(" AND c.id = :pIdCategoria   				      ")
		.add(" GROUP BY dat                                   ");
		
		Query query = this.manager.createNativeQuery(sql.toString());
		query.setParameter("pIdUsuario", idUsuario);
		query.setParameter("pDate", data);
		query.setParameter("pIdCategoria", idCategoria);
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		return Utils.listEmpty(results) ? null : ((BigDecimal) results.get(0)[1]).doubleValue();
	}

	public List<CategoriaResponse> getCategorias(Long idUsuario) {
		StringJoiner sql = new StringJoiner("\n");
		sql
		.add(" SELECT c.id,       			 ")
		.add("        c.tipo     			 ")
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
			c.setId(((Integer) o[0]).longValue());
			c.setTipoCategoria((String) o[1]);
			list.add(c);
		}
		
		return list;
	}

	public GeralResponse getDadosGerais(GeralRequest request) {
		String data = new SimpleDateFormat("MM/yyyy").format(new Date());
		GeralResponse response = new GeralResponse();
		response.setMes(Utils.getMesPorExtenso(data));
		response.setValorCreditos(Utils.duasCasasDecimais(this.getValorCreditoByDateLancamento(request.getIdUsuario(), data)));
		response.setValorDespesas(Utils.duasCasasDecimais(this.getValorDespesaByDateLancamento(request.getIdUsuario(), data)));
		response.setValorFinal(Utils.duasCasasDecimais(response.getValorCreditos() - response.getValorDespesas()));
		return response;
	}
}
