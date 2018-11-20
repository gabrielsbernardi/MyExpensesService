package com.br.myexpenses.controle;

import java.sql.SQLException;
import java.util.List;
import java.util.StringJoiner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.br.myexpenses.cript.EncriptaDecriptaAES;
import com.br.myexpenses.data.ConexaoDB;
import com.br.myexpenses.model.Usuario;
import com.br.myexpenses.utils.Utils;
import com.br.myexpenses.ws.rest.request.UsuarioInsertRequest;
import com.br.myexpenses.ws.rest.request.UsuarioRequest;
import com.br.myexpenses.ws.rest.response.UsuarioResponse;

public class UsuarioControle {

	private EntityManager manager;

	public UsuarioControle() {
		manager = ConexaoDB.getEntityManager();
	}
	
	public UsuarioResponse validarUsuario(UsuarioRequest request) throws SQLException {
		UsuarioResponse response = null;
		try {
			StringJoiner sql = new StringJoiner("\n");
			sql
			.add(" SELECT u.id, 	         ")
			.add(" 		  u.nome, 		     ")
			.add(" 		  u.senha 		     ")
			.add(" FROM usuario u 			 ")
			.add(" WHERE u.conta = :pUsuario ");
			
			Query query = this.manager.createNativeQuery(sql.toString());
			query.setParameter("pUsuario", request.getUsuario());
			
			@SuppressWarnings("unchecked")
			List<Object[]> results = query.getResultList();
			
			if (!Utils.listEmpty(results)) {
				Object[] o = results.get(0);
				response = new UsuarioResponse();
				
				if (request.getSenha().equals(EncriptaDecriptaAES.decrypt((byte[]) o[2]))) {
					response.setUsuarioValido(Boolean.TRUE);
					response.setId(((Integer) o[0]).longValue());
					response.setNome((String) o[1]);
				} else {
					response.setUsuarioValido(Boolean.FALSE);
				}
			}
			
			return response;
		} catch (Exception e) {
			response.setIsException(Boolean.TRUE);
			response.setMessage(e.getMessage());
		}
		
		return response;
	}

	public UsuarioResponse criarUsuario(UsuarioInsertRequest request) {
		UsuarioResponse ur = new UsuarioResponse();
		
		if (this.getIsContaValida(request.getConta())) {
			Usuario u = new Usuario();
			u.setNome(request.getNome());
			u.setConta(request.getConta());
			
			try {
				u.setSenha(EncriptaDecriptaAES.encrypt(request.getSenha()));
			} catch (Exception e) {
				ur.setIsException(Boolean.TRUE);
				ur.setMessage("Erro ao criptografar a senha: " + e.getMessage());
			}
			
			manager.getTransaction().begin();
			manager.persist(u);
			manager.getTransaction().commit();
		} else {
			ur.setUsuarioExistente(Boolean.TRUE);
		}
			
		return ur;
	}

	private Boolean getIsContaValida(String conta) {
		StringJoiner sql = new StringJoiner("\n");
		sql
		.add(" SELECT 1 	         	 ")
		.add(" FROM usuario u 			 ")
		.add(" WHERE u.conta = :pUsuario ");
		
		Query query = this.manager.createNativeQuery(sql.toString());
		query.setParameter("pUsuario", conta);
		
		return Utils.listEmpty(query.getResultList());
	}
}
