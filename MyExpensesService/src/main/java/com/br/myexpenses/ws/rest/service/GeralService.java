package com.br.myexpenses.ws.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.br.myexpenses.controle.GeralControle;
import com.br.myexpenses.ws.rest.request.GeralRequest;
import com.br.myexpenses.ws.rest.response.GeralResponse;
import com.br.myexpenses.ws.rest.response.GraficoLinhaResponse;
import com.br.myexpenses.ws.rest.response.GraficoPizzaResponse;

@Path("/geralService")
public class GeralService {

	@POST
	@Path("/getDadosGraficoLinha")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<GraficoLinhaResponse> getDadosGraficoLinha(GeralRequest request) throws Exception {
		GeralControle gc = new GeralControle();
		return gc.getDadosGraficoLinha(request);
	}
	
	@POST
	@Path("/getDadosGraficoPizza")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<GraficoPizzaResponse> getDadosGraficoPizza(GeralRequest request) throws Exception {
		GeralControle gc = new GeralControle();
		return gc.getDadosGraficoPizza(request);
	}
	
	@POST
	@Path("/getDadosGerais")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public GeralResponse getDadosGerais(GeralRequest request) throws Exception {
		GeralControle gc = new GeralControle();
		return gc.getDadosGerais(request);
	}
}
