package com.br.myexpenses.ws.rest.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.br.myexpenses.ws.rest.request.UsuarioRequest;
import com.br.myexpenses.ws.rest.response.CategoriaResponse;
import com.br.myexpenses.ws.rest.response.UsuarioResponse;

@Path("/usuarioService")
public class UsuarioService {
	
	@POST
	@Path("/validarUsuario")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public UsuarioResponse validarUsuario(UsuarioRequest request) throws SQLException {
		UsuarioResponse response = null;
//		try {
//			StringBuilder sql = new StringBuilder();
//			sql.append(" SELECT * FROM usuario ");
//			sql.append(" WHERE conta = '").append(request.getUsuario()).append("'");
//			
//			Conexao con = new Conexao();
//			ResultSet consulta = con.executeQuery(sql.toString());
//			
//			while (consulta.next()) {
//				response = new UsuarioResponse();
//				
//				if (request.getSenha().equals(consulta.getString("senha"))) {
//					response.setUsuarioValido(Boolean.TRUE);
//					response.setId(consulta.getInt("id"));
//					response.setNome(consulta.getString("nome"));
//				} else {
//					response.setUsuarioValido(Boolean.FALSE);
//				}
//			}
//			
//			return response;
//		} catch (Exception e) {
//			response.setIsException(Boolean.TRUE);
//			response.setMessage(e.getMessage());
//		}
		
		return null;
	}
	
	@POST
	@Path("/criarUsuario")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<CategoriaResponse> criarUsuario(UsuarioRequest request) throws SQLException {
		String sql = "SELECT TIPO, DESCRICAO FROM CATEGORIA WHERE USUARIO = 1";
//		Conexao con = new Conexao();
//		CategoriaResponse c = null;
//		List<CategoriaResponse> list = new ArrayList<CategoriaResponse>();
//		
//		ResultSet consulta = con.executeQuery(sql);
//		while (consulta.next()) {
//			c = new CategoriaResponse();												
//			c.setTipoCategoria(consulta.getString("tipo"));
//			c.setDescricao(consulta.getString("descricao"));
//			list.add(c);
//		}
		
		return null;
	}
}
