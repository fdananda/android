package com.fdananda.gitbase64conversor;

import android.util.Base64;

public class ConversorBase64 {

    public static String codificar(String string){

        String textoCodificado = "";
        textoCodificado = Base64.encodeToString(string.getBytes(), Base64.DEFAULT).replaceAll("\\n|\\r", "");

        return textoCodificado;
    }

    public static String decodificar(String string){

        String textoDecodificado = "";
        textoDecodificado = new String(Base64.decode(string, Base64.DEFAULT));

        return textoDecodificado;
    }
}
