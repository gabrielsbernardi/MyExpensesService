package com.br.myexpenses.ws.rest.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.br.myexpenses.ws.rest.request.CategoriaRequest;
import com.br.myexpenses.ws.rest.response.CategoriaResponse;

import com.br.myexpenses.data.*;

@Path("/categoriaService")
public class CategoriaService {

	@POST
	@Path("/getCategorias")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<CategoriaResponse> getCategorias(CategoriaRequest request) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM categoria ");
		sql.append(" WHERE usuario = '").append(request.getIdUsuario()).append("'");
		
		Conexao con = new Conexao();
		CategoriaResponse c = null;
		List<CategoriaResponse> list = new ArrayList<CategoriaResponse>();
		
		ResultSet consulta = con.executeQuery(sql.toString());
		while (consulta.next()) {
			c = new CategoriaResponse();
			c.setId(consulta.getInt("id"));
			c.setTipoCategoria(consulta.getString("tipo"));
			c.setDescricao(consulta.getString("descricao"));
			list.add(c);
		}
		
		return list;
	}
}
