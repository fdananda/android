package com.fdananda.crudsqlite2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class BancoDadosManipulacao extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DO_BD = "bd_CRUD_SQLite";
    public static String NOME_TABELA = "atributos";

    public BancoDadosManipulacao(@Nullable Context context) {
        super(context, NOME_DO_BD, null, VERSION);
    }

    //Para a criação da primeira versão do BD
    @Override
    public void onCreate(SQLiteDatabase db) {

        String comandoCriarTabelaSQL = "CREATE TABLE IF NOT EXISTS " +  NOME_TABELA +" (id INTEGER PRIMARY KEY AUTOINCREMENT, atributo1 VARCHAR, atributo2 VARCHAR, atributo3 VARCHAR)";

        try {
            db.execSQL(comandoCriarTabelaSQL);

        }catch (Exception e){

        }
    }

    //Para a atualização da estrutura do BD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
