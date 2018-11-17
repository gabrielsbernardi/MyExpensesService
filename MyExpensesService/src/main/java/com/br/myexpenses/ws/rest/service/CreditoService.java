package com.br.myexpenses.ws.rest.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.br.myexpenses.model.Credito;

@Path("/creditoService")
public class CreditoService {
	
	@POST
	@Path("/getCreditos")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<Credito> getCreditos() {
		Random gerador = new Random();
		Credito c = null;
		List<Credito> list = new ArrayList<Credito>();
		for (int i = 0; i < 10; i++) {
			c = new Credito();
			c.setDescricao("Crédito " + i);
			c.setValor(i + 20.0);
			c.setData(new Date());
			c.setNumParcelas(i + gerador.nextInt(30));
			list.add(c);
		}
		
		return list;
	}
}
