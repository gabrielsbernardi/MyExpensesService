package com.br.myexpenses.ws.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.br.myexpenses.ws.rest.request.CategoriaRequest;
import com.br.myexpenses.ws.rest.response.CategoriaResponse;
import com.br.myexpenses.controle.CategoriaControle;

@Path("/categoriaService")
public class CategoriaService {

	@POST
	@Path("/getCategorias")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<CategoriaResponse> getCategorias(CategoriaRequest request) throws Exception {
		CategoriaControle cc = new CategoriaControle();
		return cc.getCategorias(request);
	}
	
	@POST
	@Path("/inserirCategoria")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public CategoriaResponse inserirCategoria(CategoriaRequest request) throws Exception {
		CategoriaControle cc = new CategoriaControle();
		return cc.inserirCategoria(request);
	}
	
	@POST
	@Path("/atualizarCategoria")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public CategoriaResponse atualizarCategoria(CategoriaRequest request) throws Exception {
		CategoriaControle cc = new CategoriaControle();
		return cc.atualizarCategoria(request);
	}
	
	@POST
	@Path("/excluirCategoria")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public CategoriaResponse excluirCategoria(Long id) throws Exception {
		CategoriaControle cc = new CategoriaControle();
		return cc.excluirCategoria(id);
	}
}
