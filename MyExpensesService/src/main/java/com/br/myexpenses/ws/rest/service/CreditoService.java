package com.br.myexpenses.ws.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.br.myexpenses.controle.CreditoControle;
import com.br.myexpenses.controle.DespesaControle;
import com.br.myexpenses.ws.rest.request.CreditoRequest;
import com.br.myexpenses.ws.rest.request.DespesaRequest;
import com.br.myexpenses.ws.rest.response.CreditoResponse;
import com.br.myexpenses.ws.rest.response.DespesaCategoriaResponse;
import com.br.myexpenses.ws.rest.response.DespesaResponse;

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
		
//		Conexao con = new Conexao();
//		CreditoResponse c = null;
//		List<CreditoResponse> list = new ArrayList<CreditoResponse>();
//		
//		ResultSet consulta = con.executeQuery(sql.toString());
//		while (consulta.next()) {
//			c = new CreditoResponse();
//			c.setId(consulta.getInt("id"));
//			c.setDescricao(consulta.getString("descricao"));
//			c.setData(consulta.getDate("data_credito"));
//			c.setValor(consulta.getDouble("valor"));
//			c.setNumParcela(consulta.getInt("parcela"));
//			list.add(c);
//		}
//		
		return null;
	}
	
	/*@POST
	@Path("/getCreditos")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<DespesaResponse> getCreditos(CreditoRequest request) throws Exception {
		CreditoControle cc = new CreditoControle();
		return dc.getDespesas(request.getIdUsuario());
	}
	
	@POST
	@Path("/inserirCredito")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public DespesaResponse inserirCredito(DespesaRequest request) throws Exception {
		DespesaControle dc = new DespesaControle();
		return dc.inserirDespesa(request);
	}
	
	@POST
	@Path("/excluirCredito")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public DespesaResponse excluirCredito(Long id) throws Exception {
		DespesaControle dc = new DespesaControle();
		return dc.excluirDespesa(id);
	}*/
}
