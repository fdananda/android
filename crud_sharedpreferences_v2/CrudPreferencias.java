package com.fdananda.gitcrudsharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class CrudPreferencias {

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String NOME_DO_ARQUIVO = "Arquivo_CRUD_SharedPreferences";
    private final String ATRIBUTO1 = "atributo1";
    private final String ATRIBUTO2 = "atributo2";
    private final String ATRIBUTO3 = "atributo3";
    private Atributo atributo;

    public CrudPreferencias(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(NOME_DO_ARQUIVO, 0);
        editor = sharedPreferences.edit();
    }

    public void create(String atributo1, String atributo2, String atributo3){
        editor.putString(ATRIBUTO1, atributo1);
        editor.putString(ATRIBUTO2, atributo2);
        editor.putString(ATRIBUTO3, atributo3);
        editor.commit();
    }

    public Atributo read(){

        atributo = new Atributo();

        String atributo1, atributo2, atributo3;

        atributo1 = sharedPreferences.getString(ATRIBUTO1, "");
        atributo2 = sharedPreferences.getString(ATRIBUTO2, "");
        atributo3 = sharedPreferences.getString(ATRIBUTO3, "");

        atributo.setAtributo1(atributo1);
        atributo.setAtributo2(atributo2);
        atributo.setAtributo3(atributo3);

        return atributo;
    }

    public void update(String atributo1, String atributo2, String atributo3){

        editor.putString(ATRIBUTO1, atributo1);
        editor.putString(ATRIBUTO2, atributo2);
        editor.putString(ATRIBUTO3, atributo3);
        editor.commit();

    }

    public void delete(){
        editor.putString(ATRIBUTO1, "");
        editor.putString(ATRIBUTO2, "");
        editor.putString(ATRIBUTO3, "");
        editor.commit();
    }
}
