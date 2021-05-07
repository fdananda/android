package com.fdananda.gitcrudsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String NOME_DO_BD = "bd_CRUD_SQLite";
    private EditText editTextAtributo1, editTextAtributo2,  editTextAtributo3;
    private TextView textViewEditar, textViewExcluir;
    private Button buttonGravarEdicao, buttonGravarInclusao;
    private ImageButton imageButtonCreate, imageButtonRead;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAtributo1 = findViewById(R.id.editTextAtributo1);
        editTextAtributo2 = findViewById(R.id.editTextAtributo2);
        editTextAtributo3 = findViewById(R.id.editTextAtributo3);

        imageButtonCreate = findViewById(R.id.imageButtonSalvar);
        imageButtonRead   = findViewById(R.id.imageButtonListar);

        textViewEditar          = findViewById(R.id.textViewEditar);
        textViewExcluir          = findViewById(R.id.textViewExcluir);
        buttonGravarEdicao      = findViewById(R.id.buttonEditar);
        buttonGravarInclusao      = findViewById(R.id.buttonGravar);

        buttonGravarInclusao.setVisibility(View.GONE);
        buttonGravarEdicao.setVisibility(View.GONE);
        textViewEditar.setVisibility(View.GONE);
        textViewExcluir.setVisibility(View.GONE);
        editTextAtributo1.setVisibility(View.GONE);
        editTextAtributo2.setVisibility(View.GONE);
        editTextAtributo3.setVisibility(View.GONE);

    }

    private ArrayList<Atributo> listar() {

        ArrayList<Atributo> listaAtributos = new ArrayList<Atributo>();
        listaAtributos.clear();

        //Banco de dados
        try {

            SQLiteDatabase bd = openOrCreateDatabase(NOME_DO_BD, MODE_PRIVATE, null);
            String consultaListar = "SELECT * from atributos";
            Cursor cursor = bd.rawQuery(consultaListar, null);

            if(consultaListar.isEmpty()){
                Toast.makeText(getApplicationContext(), "Você ainda não possui nenhum registro cadastrado!", Toast.LENGTH_SHORT).show();
            }else{

                int indiceId = cursor.getColumnIndex("id");
                int indiceAtributo1 = cursor.getColumnIndex("atributo1");
                int indiceAtributo2 = cursor.getColumnIndex("atributo2");
                int indiceAtributo3 = cursor.getColumnIndex("atributo3");

                cursor.moveToFirst();
                while (cursor!=null){
                    Integer id = cursor.getInt(indiceId);
                    String atributo1 = cursor.getString(indiceAtributo1);
                    String atributo2 = cursor.getString(indiceAtributo2);
                    String atributo3 = cursor.getString(indiceAtributo3);

                    Atributo atributoIndividual = new Atributo(atributo1, atributo2, atributo3, id);
                    listaAtributos.add(atributoIndividual);
                    cursor.moveToNext();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return listaAtributos;
    }

    public void create(View view) {

        editTextAtributo1.setVisibility(View.VISIBLE);
        editTextAtributo2.setVisibility(View.VISIBLE);
        editTextAtributo3.setVisibility(View.VISIBLE);
        buttonGravarInclusao.setVisibility(View.VISIBLE);
        textViewEditar.setVisibility(View.GONE);
        textViewExcluir.setVisibility(View.GONE);
        buttonGravarEdicao.setVisibility(View.GONE);
        listView = findViewById(R.id.listViewAtributos);

        ArrayList<Atributo> atributos = listar();
        ArrayAdapter adapter = new AdapterCustomizado(this, atributos);
        listView.setAdapter(adapter);

        editTextAtributo1.setText("");
        editTextAtributo2.setText("");
        editTextAtributo3.setText("");

        try{

            SQLiteDatabase bd = openOrCreateDatabase(NOME_DO_BD, MODE_PRIVATE, null);
            //String comandoSQL = "DROP TABLE atributos";
            String comandoSQL = "CREATE TABLE IF NOT EXISTS atributos (id INTEGER PRIMARY KEY AUTOINCREMENT, atributo1 VARCHAR, atributo2 VARCHAR, atributo3 VARCHAR)";
            bd.execSQL(comandoSQL);

            buttonGravarInclusao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String atributo1, atributo2, atributo3;

                    atributo1 = editTextAtributo1.getText().toString();
                    atributo2 = editTextAtributo2.getText().toString();
                    atributo3 = editTextAtributo3.getText().toString();

                    if (atributo1.isEmpty() || atributo1 == "") {
                        Toast.makeText(getApplicationContext(), "Preencha um Texto válido!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (atributo2.isEmpty() || atributo1 == "") {
                            Toast.makeText(getApplicationContext(), "Preencha um Texto válido!", Toast.LENGTH_SHORT).show();
                        } else {
                            if (atributo3.isEmpty() || atributo1 == "") {
                                Toast.makeText(getApplicationContext(), "Preencha um Texto válido!", Toast.LENGTH_SHORT).show();
                            } else {

                                //Inserir dados
                                String comandoInserirSQL = "INSERT INTO atributos(atributo1, atributo2, atributo3) VALUES ('" + atributo1 + "', '" + atributo2 + "', '" + atributo3 + "')";
                                bd.execSQL(comandoInserirSQL);

                                Toast.makeText(getApplicationContext(), "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();

                                editTextAtributo1.setText("");
                                editTextAtributo2.setText("");
                                editTextAtributo3.setText("");

                                create(imageButtonCreate);

                            }
                        }
                    }
                }
            });
        }catch (Exception e){
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                create(imageButtonCreate);
            }
        });
    }


    public void read(View view){

        listView = findViewById(R.id.listViewAtributos);

        ArrayList<Atributo> atributos = listar();
        ArrayAdapter adapter = new AdapterCustomizado(this, atributos);
        listView.setAdapter(adapter);

        editTextAtributo1.setText("");
        editTextAtributo2.setText("");
        editTextAtributo3.setText("");

        editTextAtributo1.setVisibility(View.GONE);
        editTextAtributo2.setVisibility(View.GONE);
        editTextAtributo3.setVisibility(View.GONE);
        buttonGravarEdicao.setVisibility(View.GONE);
        buttonGravarInclusao.setVisibility(View.GONE);
        textViewEditar.setVisibility(View.GONE);
        textViewExcluir.setVisibility(View.GONE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                read(imageButtonRead);
            }
        });

    }

    public void update(View view){

        textViewEditar.setVisibility(View.VISIBLE);
        textViewExcluir.setVisibility(View.GONE);
        editTextAtributo1.setVisibility(View.GONE);
        editTextAtributo2.setVisibility(View.GONE);
        editTextAtributo3.setVisibility(View.GONE);
        buttonGravarInclusao.setVisibility(View.GONE);

        listView = findViewById(R.id.listViewAtributos);

        ArrayList<Atributo> atributos = listar();
        ArrayAdapter adapter = new AdapterCustomizado(this, atributos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                editTextAtributo1.setVisibility(View.VISIBLE);
                editTextAtributo2.setVisibility(View.VISIBLE);
                editTextAtributo3.setVisibility(View.VISIBLE);
                buttonGravarEdicao.setVisibility(View.VISIBLE);
                textViewEditar.setVisibility(View.GONE);

                editTextAtributo1.setText(atributos.get(position).getAtributo1());
                editTextAtributo2.setText(atributos.get(position).getAtributo2());
                editTextAtributo3.setText(atributos.get(position).getAtributo3());

                buttonGravarEdicao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try{
                            SQLiteDatabase bd = openOrCreateDatabase(NOME_DO_BD, MODE_PRIVATE, null);

                            String atributo1Alterado, atributo2Alterado, atributo3Alterado, idAlterado;

                            atributo1Alterado = editTextAtributo1.getText().toString();
                            atributo2Alterado = editTextAtributo2.getText().toString();
                            atributo3Alterado = editTextAtributo3.getText().toString();

                            if (atributo1Alterado.isEmpty() || atributo1Alterado == "") {
                                Toast.makeText(getApplicationContext(), "Preencha um Texto válido!", Toast.LENGTH_SHORT).show();
                            } else {
                                if (atributo2Alterado.isEmpty() || atributo1Alterado == "") {
                                    Toast.makeText(getApplicationContext(), "Preencha um Texto válido!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (atributo3Alterado.isEmpty() || atributo1Alterado == "") {
                                        Toast.makeText(getApplicationContext(), "Preencha um Texto válido!", Toast.LENGTH_SHORT).show();
                                    } else {

                                        //Atualizar dados
                                        String comandoAtualizarSQL = "UPDATE atributos SET atributo1 = '" + atributo1Alterado + "', atributo2 = '" + atributo2Alterado + "', atributo3 = '" + atributo3Alterado + "' WHERE id = '" + atributos.get(position).getId() + "'";
                                        bd.execSQL(comandoAtualizarSQL);

                                        editTextAtributo1.setText("");
                                        editTextAtributo2.setText("");
                                        editTextAtributo3.setText("");

                                        editTextAtributo1.setVisibility(View.GONE);
                                        editTextAtributo2.setVisibility(View.GONE);
                                        editTextAtributo3.setVisibility(View.GONE);

                                        Toast.makeText(getApplicationContext(), "Dados alterados com sucesso!", Toast.LENGTH_SHORT).show();

                                        buttonGravarEdicao.setVisibility(View.GONE);
                                        textViewEditar.setVisibility(View.VISIBLE);

                                        update(imageButtonRead);
                                    }
                                }
                            }
                        }catch (Exception e){
                        }
                    }
                });
            }
        });

        editTextAtributo1.setText("");
        editTextAtributo2.setText("");
        editTextAtributo3.setText("");
    }

    public void delete(View view){

        textViewEditar.setVisibility(View.GONE);
        editTextAtributo1.setVisibility(View.GONE);
        editTextAtributo2.setVisibility(View.GONE);
        editTextAtributo3.setVisibility(View.GONE);
        textViewExcluir.setVisibility(View.VISIBLE);
        buttonGravarEdicao.setVisibility(View.GONE);
        buttonGravarInclusao.setVisibility(View.GONE);

        listView = findViewById(R.id.listViewAtributos);

        ArrayList<Atributo> atributos = listar();
        ArrayAdapter adapter = new AdapterCustomizado(this, atributos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(parent.getContext());
                alertDialog.setCancelable(true);
                alertDialog.setIcon(android.R.drawable.ic_menu_delete);
                alertDialog.setTitle("Excluir dados");
                alertDialog.setMessage("Ao confirmar, os atributos a seguir serão excluídos: " +
                        "\n" + atributos.get(position).getAtributo1() +
                        "\n" + atributos.get(position).getAtributo2() +
                        "\n" + atributos.get(position).getAtributo3() +
                        "\n\nDeseja continuar?");

                alertDialog.setPositiveButton("SIM", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                try {
                                    SQLiteDatabase bd = openOrCreateDatabase(NOME_DO_BD, MODE_PRIVATE, null);
                                    String comandoExcluirSQL = "DELETE from atributos WHERE id = '" + atributos.get(position).getId() + "'";
                                    bd.execSQL(comandoExcluirSQL);

                                    editTextAtributo1.setText("");
                                    editTextAtributo2.setText("");
                                    editTextAtributo3.setText("");

                                    Toast.makeText(getApplicationContext(), "Dados excluídos com sucesso!", Toast.LENGTH_SHORT).show();

                                    delete(imageButtonRead);

                                }catch (Exception e){
                                }
                            }
                        });
                alertDialog.setNegativeButton("NÃO", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                alertDialog.create();
                alertDialog.show();
            }
        });
    }
}
