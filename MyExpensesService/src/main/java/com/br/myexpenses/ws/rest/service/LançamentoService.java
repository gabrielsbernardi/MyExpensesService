package com.br.myexpenses.ws.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.br.myexpenses.ws.rest.request.GastoRequest;
import com.br.myexpenses.ws.rest.response.DespesaResponse;

@Path("/lancamentoService")
public class LançamentoService {
	
	@POST
	@Path("/getGastos")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<DespesaResponse> getGastos(GastoRequest request) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT d.*, c.id AS id_categoria, c.tipo AS tipo_categoria FROM despesa d ");
		sql.append(" INNER JOIN categoria c ON (c.id = d.categoria) ");
		sql.append(" WHERE d.usuario = '").append(request.getIdUsuario()).append("'");
		
//		Conexao con = new Conexao();
//		DespesaResponse d = null;
//		List<DespesaResponse> list = new ArrayList<DespesaResponse>();
//		
//		ResultSet consulta = con.executeQuery(sql.toString());
//		while (consulta.next()) {
//			d = new DespesaResponse();
//			d.setId(consulta.getInt("id"));
//			d.setDescricao(consulta.getString("descricao"));
//			d.setData(consulta.getDate("data_compra"));
//			d.setValor(consulta.getDouble("valor"));
//			d.setNumParcela(consulta.getInt("parcela"));
//			d.setLocal(consulta.getString("local_compra"));
//			
//			DespesaCategoriaResponse dcr = new DespesaCategoriaResponse();
//			dcr.setId(consulta.getInt("id_categoria"));
//			dcr.setTipoCategoria(consulta.getString("tipo_categoria"));
//			
//			d.setCategoria(dcr);
//			
//			list.add(d);
//		}
		
		return null;
	}
	
}
