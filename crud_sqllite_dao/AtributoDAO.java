package com.fdananda.crudsqlite2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class AtributoDAO implements IAtributoDAO {

    private SQLiteDatabase writeDB;
    private SQLiteDatabase readDB;

    public AtributoDAO(Context context) {
        BancoDadosManipulacao bd = new BancoDadosManipulacao(context);
        writeDB = bd.getWritableDatabase();
        readDB  = bd.getReadableDatabase();
    }

    @Override
    public boolean create(Atributo atributo) {

        ContentValues cv = new ContentValues();
        cv.put("atributo1", atributo.getAtributo1());
        cv.put("atributo2", atributo.getAtributo2());
        cv.put("atributo3", atributo.getAtributo3());

        try{
            writeDB.insert(BancoDadosManipulacao.NOME_TABELA, null, cv);

        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Atributo atributo) {

        ContentValues cv = new ContentValues();
        cv.put("atributo1", atributo.getAtributo1().toString());
        cv.put("atributo2", atributo.getAtributo2().toString());
        cv.put("atributo3", atributo.getAtributo3().toString());

        try{
            String clauseWhere = "id=?";
            String[] whereArgs = {String.valueOf(atributo.getId())};

            writeDB.update(BancoDadosManipulacao.NOME_TABELA, cv, clauseWhere, whereArgs);

        }catch (Exception e){

            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Atributo atributo) {
        try{
            String clauseWhere = "id=?";
            String[] whereArgs = {String.valueOf(atributo.getId())};

            writeDB.delete(BancoDadosManipulacao.NOME_TABELA, clauseWhere, whereArgs);

        }catch (Exception e){

            return false;
        }
        return true;
    }

    @Override
    public List<Atributo> read() {
        List<Atributo> atributos = new ArrayList<>();

        String consultaListar = "SELECT * from " + BancoDadosManipulacao.NOME_TABELA + "";
        Cursor cursor = readDB.rawQuery(consultaListar, null);

            while (cursor.moveToNext()){

                int indiceId = cursor.getColumnIndex("id");
                int indiceAtributo1 = cursor.getColumnIndex("atributo1");
                int indiceAtributo2 = cursor.getColumnIndex("atributo2");
                int indiceAtributo3 = cursor.getColumnIndex("atributo3");

                Integer id = cursor.getInt(indiceId);
                String atributo1 = cursor.getString(indiceAtributo1);
                String atributo2 = cursor.getString(indiceAtributo2);
                String atributo3 = cursor.getString(indiceAtributo3);

                Atributo atributoIndividual = new Atributo();
                atributoIndividual.setId(id);
                atributoIndividual.setAtributo1(atributo1);
                atributoIndividual.setAtributo2(atributo2);
                atributoIndividual.setAtributo3(atributo3);

                atributos.add(atributoIndividual);
        }
        return atributos;
    }
}
