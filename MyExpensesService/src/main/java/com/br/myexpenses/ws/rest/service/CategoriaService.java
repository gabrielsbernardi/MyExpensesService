package com.br.myexpenses.ws.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.br.myexpenses.model.Categoria;

@Path("/categoriaService")
public class CategoriaService {

	@POST
	@Path("/getCategorias")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<Categoria> getCategorias() {
		Categoria c = null;
		List<Categoria> list = new ArrayList<Categoria>();
		for (int i = 0; i < 10; i++) {
			c = new Categoria();
			c.setTipo("Tipo " + i);
			c.setDescricao("Descricão Categoria " + i);
			list.add(c);
		}
		return list;
	}
}
