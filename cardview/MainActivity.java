package com.fdananda.gitcardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Cardview> listaCardview = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView    = findViewById(R.id.recyclerView);

        //Passo 1 - Criação de um arquivo xml com o layout personalizado a ser utilizado.
        //          No nosso exemplo, arquivo com o nome adapter.xml dentro do package layout
        //          O componente Cardview é incluído dentro desse layout que é criado

        //Passo 2 -  Criação um model com a Class que será utilizada.
        //          No nosso exemplo, arquivo com o nome Cardview.java

        //Passo 3 - Criar a listagem de cardview
        this.criarCardview();

        //Passo 4 - Definição do adapter
        // - Cria uma class adapter, instancia e em seguida define
        Adapter adapter = new Adapter(listaCardview);


        //Passo 5 - Configuração do layout
        //Primeiro modelo com RecyclerView.LayoutManager e orientação vertical
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        //Segundo modelo com LinearLayoutManager e orientação horizontal
        //LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        //layoutManager2.setOrientation(LinearLayout.HORIZONTAL);

        //Terceiro modelo com RecyclerView.LayoutManager do tipo Grid e com 2 colunas
        RecyclerView.LayoutManager layoutManager3 = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager3);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);
    }

    public void criarCardview(){

        Cardview cardview = new Cardview("Título 1", "Descrição 1", "Autor 1", R.drawable.foto1);
        this.listaCardview.add(cardview);

        cardview = new Cardview("Título 2", "Descrição 2", "Autor 2", R.drawable.foto2);
        this.listaCardview.add(cardview);

        cardview = new Cardview("Título 3", "Descrição 3", "Autor 3", R.drawable.foto3);
        this.listaCardview.add(cardview);

        cardview = new Cardview("Título 4", "Descrição 4", "Autor 4", R.drawable.foto4);
        this.listaCardview.add(cardview);

        cardview = new Cardview("Título 5", "Descrição 5", "Autor 5", R.drawable.foto5);
        this.listaCardview.add(cardview);

        cardview = new Cardview("Título 6", "Descrição 6", "Autor 6", R.drawable.foto6);
        this.listaCardview.add(cardview);
    }
}
