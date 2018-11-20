package com.br.myexpenses.ws.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.br.myexpenses.controle.DespesaControle;
import com.br.myexpenses.ws.rest.request.DespesaRequest;
import com.br.myexpenses.ws.rest.response.DespesaCategoriaResponse;
import com.br.myexpenses.ws.rest.response.DespesaResponse;

@Path("/despesaService")
public class DespesaService {
	
	@POST
	@Path("/getDespesas")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<DespesaResponse> getDespesas(DespesaRequest request) throws Exception {
		DespesaControle dc = new DespesaControle();
		return dc.getDespesas(request.getIdUsuario());
	}
	
	@POST
	@Path("/getDespesasCategorias")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<DespesaCategoriaResponse> getDespesasCategorias(DespesaRequest request) throws Exception {
		DespesaControle dc = new DespesaControle();
		return dc.getDespesasCategorias(request.getIdUsuario());
	}
	
	@POST
	@Path("/inserirDespesa")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public DespesaResponse inserirDespesa(DespesaRequest request) throws Exception {
		DespesaControle dc = new DespesaControle();
		return dc.inserirDespesa(request);
	}
	
	@POST
	@Path("/excluirDespesa")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public DespesaResponse excluirDespesa(Long id) throws Exception {
		DespesaControle dc = new DespesaControle();
		return dc.excluirDespesa(id);
	}
}
