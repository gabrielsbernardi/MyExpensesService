package com.br.myexpenses.data;

import javax.persistence.*;

public class ConexaoDB {
	private static EntityManagerFactory manager;
	
	static {
		manager = Persistence.createEntityManagerFactory("myexpenses");
		manager.createEntityManager();		
	}
	
	/**
	 * 
	 * @return EntityManager instanciado
	 */
	public static EntityManager getEntityManager() {
		return manager.createEntityManager();
	}
}
