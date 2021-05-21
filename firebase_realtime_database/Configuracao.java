package com.fdananda.gitfirebaserealtimedatabase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Configuracao {

    private static FirebaseAuth autenticacao;
    private static FirebaseDatabase database;
    private static DatabaseReference atributosRef;

    public static DatabaseReference getDatabase(){

        if (database == null){

            database = FirebaseDatabase.getInstance();
            atributosRef = database.getReference("atributos");
        }
        return atributosRef;
    }

    public static FirebaseAuth getAutenticacao(){

        if(autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }
}
