package com.br.myexpenses.ws.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.br.myexpenses.controle.CreditoControle;
import com.br.myexpenses.controle.DespesaControle;
import com.br.myexpenses.ws.rest.request.CreditoRequest;
import com.br.myexpenses.ws.rest.request.DespesaRequest;
import com.br.myexpenses.ws.rest.response.CreditoResponse;
import com.br.myexpenses.ws.rest.response.DespesaResponse;

@Path("/creditoService")
public class CreditoService {
	
	@POST
	@Path("/getCreditos")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<CreditoResponse> getCreditos(CreditoRequest request) throws Exception {
		CreditoControle cc = new CreditoControle();
		return cc.getCreditos(request.getIdUsuario());
	}
	
	@POST
	@Path("/inserirCredito")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public CreditoResponse inserirCredito(CreditoRequest request) throws Exception {
		CreditoControle cc = new CreditoControle();
		return cc.inserirCredito(request);
	}
	
	@POST
	@Path("/atualizarCredito")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public CreditoResponse atualizarCredito(CreditoRequest request) throws Exception {
		CreditoControle dc = new CreditoControle();
		return dc.atualizar(request);
	}
	
	@POST
	@Path("/excluirCredito")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public CreditoResponse excluirCredito(Long id) throws Exception {
		CreditoControle cc = new CreditoControle();
		return cc.excluirCredito(id);
	}
}
