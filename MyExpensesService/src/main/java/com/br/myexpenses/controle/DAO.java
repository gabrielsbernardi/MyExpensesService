package com.br.myexpenses.controle;

public interface DAO<T> {
	boolean inserir(T obj) throws Exception;
	boolean atualizar(T obj, int id) throws Exception;
	boolean excluir(int id) throws Exception;
}
