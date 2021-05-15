package com.fdananda.gitfirebaseauthentication;

import com.google.firebase.auth.FirebaseAuth;

public class Configuracao {

    private static FirebaseAuth autenticacao;

    public static FirebaseAuth getAutenticacao(){

        if(autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }
}
