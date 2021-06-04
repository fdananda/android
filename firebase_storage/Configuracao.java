package com.fdananda.gitfirebasestorage;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Configuracao {

    private static FirebaseAuth autenticacao;
    private static FirebaseDatabase database;
    private static DatabaseReference imagensRef;
    private static StorageReference storageReference;
    private static StorageReference imagensStorage;

    public static DatabaseReference getDatabase(){

        if (database == null){

            database = FirebaseDatabase.getInstance();
            imagensRef = database.getReference("imagens");
        }
        return imagensRef;
    }

    public static FirebaseAuth getAutenticacao(){

        if(autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }

    public static StorageReference getStorage(){
        if (storageReference == null){
            storageReference = FirebaseStorage.getInstance().getReference();
            imagensStorage = storageReference.child("nome_da_pasta");
        }
        return imagensStorage;
    }
}
