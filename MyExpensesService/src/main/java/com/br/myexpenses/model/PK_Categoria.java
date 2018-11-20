package com.br.myexpenses.model;

import java.io.Serializable;

public class PK_Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

    private int PK_USUARIO;
    private int PK_ID;

    public PK_Categoria()
    {}

    public PK_Categoria(int PK_USUARIO, int PK_ID) {
        this.PK_USUARIO = PK_USUARIO;
        this.PK_ID = PK_ID;
    }
}
