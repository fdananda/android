package com.fdananda.gitfirebaserealtimedatabase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import java.util.HashMap;
import java.util.Map;

public class Atributo {

    private String atributo1;
    private String atributo2;
    private String atributo3;
    private String identificador;

    public Atributo() {
    }

    public void salvar(){

        DatabaseReference atributosRef = Configuracao.getDatabase();
        //Ao usar o push() no lugar do child(), é gerado um identificador único ao salvar
        atributosRef.push().setValue(this);
    }

    public void editar(){

        DatabaseReference atributosRefAlterar = Configuracao.getDatabase().child(this.getIdentificador());

        Map<String, Object> valAtributos = toMap();
        atributosRefAlterar.updateChildren(valAtributos);
    }

    public void excluir(){

        DatabaseReference atributosRefSelecionado = Configuracao.getDatabase().child(this.getIdentificador());
        atributosRefSelecionado.removeValue();
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> atributoMap = new HashMap<>();
        atributoMap.put("atributo1", getAtributo1());
        atributoMap.put("atributo2", getAtributo2());
        atributoMap.put("atributo3", getAtributo3());

        return atributoMap;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getAtributo1() {
        return atributo1;
    }

    public void setAtributo1(String atributo1) {
        this.atributo1 = atributo1;
    }

    public String getAtributo2() {
        return atributo2;
    }

    public void setAtributo2(String atributo2) {
        this.atributo2 = atributo2;
    }

    public String getAtributo3() {
        return atributo3;
    }

    public void setAtributo3(String atributo3) {
        this.atributo3 = atributo3;
    }
}
