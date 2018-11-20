package com.br.myexpenses.ws.rest.service;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.br.myexpenses.controle.UsuarioControle;
import com.br.myexpenses.ws.rest.request.UsuarioInsertRequest;
import com.br.myexpenses.ws.rest.request.UsuarioRequest;
import com.br.myexpenses.ws.rest.response.UsuarioResponse;

@Path("/usuarioService")
public class UsuarioService {
	
	@POST
	@Path("/validarUsuario")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public UsuarioResponse validarUsuario(UsuarioRequest request) throws SQLException {
		UsuarioControle uc = new UsuarioControle();
		return uc.validarUsuario(request);
	}
	
	@POST
	@Path("/criarUsuario")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public UsuarioResponse criarUsuario(UsuarioInsertRequest request) throws SQLException {
		UsuarioControle uc = new UsuarioControle();
		return uc.criarUsuario(request);
	}
}
