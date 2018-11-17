package com.br.myexpenses.ws.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.br.myexpenses.ws.rest.response.CategoriaResponse;

@Path("/categoriaService")
public class CategoriaService {

	@POST
	@Path("/getCategorias")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<CategoriaResponse> getCategorias() {
		CategoriaResponse c = null;
		List<CategoriaResponse> list = new ArrayList<CategoriaResponse>();
		for (int i = 0; i < 50; i++) {
			c = new CategoriaResponse();
			c.setTipoCategoria("Tipo " + i);
			c.setDescricao("Descricão Categoria " + i);
			list.add(c);
		}
		return list;
	}
}
