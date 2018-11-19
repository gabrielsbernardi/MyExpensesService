package com.br.myexpenses.ws.rest.response;

public class GastoResponse extends DefaultResponse {

	//Ao criar a despesa já faz a divisão do gasto e salva na table o id da despesa referencia
	//Quando editado a despesa, exclui todos os dados do gasto que faz referencia a mesma
	//e recalcula de novo
}
