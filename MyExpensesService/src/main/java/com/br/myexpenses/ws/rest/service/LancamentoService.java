package com.br.myexpenses.ws.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.br.myexpenses.controle.LancamentoControle;
import com.br.myexpenses.ws.rest.request.LancamentoRequest;
import com.br.myexpenses.ws.rest.response.LancamentoResponse;

@Path("/lancamentoService")
public class LancamentoService {
	
	@POST
	@Path("/getLancamentos")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<LancamentoResponse> getLancamentos(LancamentoRequest request) throws Exception {
		LancamentoControle lc = new LancamentoControle();
		return lc.getLancamentos(request);
	}
	
	@POST
	@Path("/getCreditosDespesaLancamento")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public LancamentoResponse getCreditosDespesaLancamento(LancamentoRequest request) throws Exception {
		LancamentoControle lc = new LancamentoControle();
		return lc.getCreditosDespesaLancamento(request);
	}
}
