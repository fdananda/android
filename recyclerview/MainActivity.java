package com.fdananda.gitrecyclerview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Objeto> listaObjetos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView    = findViewById(R.id.recyclerView);

        //Passo 1 - Criação de um arquivo xml com o layout personalizado a ser utilizado.
        //          No nosso exemplo, arquivo com o nome adapter.xml dentro do package layout

        //Passo 2 -  Criação um model com a Class que será utilizada.
        //          No nosso exemplo, arquivo com o nome Objeto.java

        //Passo 3 - Criar a listagem de objetos
        this.criarObjetos();

        //Passo 4 - Criação de um adapter
        //Passo 4.1 - Criar uma Java Class (no nosso caso, chamada Adapter.java)
        //Passo 4.2 - Instanciar o adapter
        Adapter adapter = new Adapter(listaObjetos);

        //Passo 5 - Configuração do RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                getApplicationContext()); //Context
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true); //Otimiza o RecyclerView
        //Para inclusão de um divider via código. Optamos por incluir no próprio layout do adapter
        // recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

        //Passo 6 - Inclusão de evento de clique
        //Utilizado a classe RecyclerItemClickListener - disponível em https://github.com/jamiltondamasceno/RecyclerItemClickListener/blob/master/RecyclerItemClickListener.java
        recyclerView.addOnItemTouchListener(
            new RecyclerItemClickListener(
                    getApplicationContext(), //Context
                    recyclerView, //O RecyclerView sobre o qual será aplicado o evento
                    new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {//Tratamento de clique simples

                            Objeto objeto = listaObjetos.get(position);

                            Toast.makeText(MainActivity.this, objeto.getAtributo1(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onLongItemClick(View view, int position) {//Tratamento de clique longo

                            Objeto objeto = listaObjetos.get(position);

                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                            alertDialog.setCancelable(true);
                            alertDialog.setIcon(android.R.drawable.btn_star_big_on);
                            alertDialog.setTitle(objeto.getAtributo1());
                            alertDialog.setMessage(objeto.getAtributo2() + "\n" + objeto.getAtributo3());

                            alertDialog.setPositiveButton("OK", new
                                    DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                            alertDialog.create();
                            alertDialog.show();

                        }

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        }
                    }
            )
        );
    }

    public void criarObjetos(){
        Objeto objeto = new Objeto("1 - Texto do Atributo 1", "1 - Texto do Atributo 2", "1 - Texto do Atributo 3");
        this.listaObjetos.add(objeto);

        objeto = new Objeto("2 - Texto do Atributo 1", "2 - Texto do Atributo 2", "2 - Texto do Atributo 3");
        this.listaObjetos.add(objeto);

        objeto = new Objeto("3 - Texto do Atributo 1", "3 - Texto do Atributo 2", "3 - Texto do Atributo 3");
        this.listaObjetos.add(objeto);

        objeto = new Objeto("4 - Texto do Atributo 1", "4 - Texto do Atributo 2", "4 - Texto do Atributo 3");
        this.listaObjetos.add(objeto);

        objeto = new Objeto("5 - Texto do Atributo 1", "5 - Texto do Atributo 2", "5 - Texto do Atributo 3");
        this.listaObjetos.add(objeto);

        objeto = new Objeto("6 - Texto do Atributo 1", "6 - Texto do Atributo 2", "6 - Texto do Atributo 3");
        this.listaObjetos.add(objeto);

        objeto = new Objeto("7 - Texto do Atributo 1", "7 - Texto do Atributo 2", "7 - Texto do Atributo 3");
        this.listaObjetos.add(objeto);
    }
}
