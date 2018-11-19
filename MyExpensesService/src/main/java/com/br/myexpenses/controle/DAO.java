package com.br.myexpenses.controle;


import java.util.List;

public interface DAO<T> {
	boolean inserir(T obj) throws Exception;
	boolean atualizar(T obj, int id) throws Exception;
	boolean excluir(int id) throws Exception;
	T buscar(int id);
	List<T> listar();
}
