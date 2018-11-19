package com.br.myexpenses.ws.rest.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.br.myexpenses.data.Conexao;
import com.br.myexpenses.ws.rest.request.CreditoRequest;
import com.br.myexpenses.ws.rest.response.CreditoResponse;

@Path("/creditoService")
public class CreditoService {
	
	@POST
	@Path("/getCreditos")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<CreditoResponse> getCreditos(CreditoRequest request) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM credito ");
		sql.append(" WHERE usuario = '").append(request.getIdUsuario()).append("'");
		
		Conexao con = new Conexao();
		CreditoResponse c = null;
		List<CreditoResponse> list = new ArrayList<CreditoResponse>();
		
		ResultSet consulta = con.executeQuery(sql.toString());
		while (consulta.next()) {
			c = new CreditoResponse();
			c.setId(consulta.getInt("id"));
			c.setDescricao(consulta.getString("descricao"));
			c.setData(consulta.getDate("data_credito"));
			c.setValor(consulta.getDouble("valor"));
			c.setNumParcela(consulta.getInt("parcela"));
			list.add(c);
		}
		
		return list;
	}
}
