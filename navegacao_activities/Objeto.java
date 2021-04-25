package com.fdananda.gitnavegacao;

import java.io.Serializable;

public class Objeto implements Serializable {

    private String atributoObjetoTexto;
    private int atributoObjetoNumero;

    public Objeto(String atributoObjetoTexto, int atributoObjetoNumero) {
        this.atributoObjetoTexto = atributoObjetoTexto;
        this.atributoObjetoNumero = atributoObjetoNumero;
    }

    public String getAtributoObjetoTexto() {
        return atributoObjetoTexto;
    }

    public void setAtributoObjetoTexto(String atributoObjetoTexto) {
        this.atributoObjetoTexto = atributoObjetoTexto;
    }

    public int getAtributoObjetoNumero() {
        return atributoObjetoNumero;
    }

    public void setAtributoObjetoNumero(int atributoObjetoNumero) {
        this.atributoObjetoNumero = atributoObjetoNumero;
    }
}
