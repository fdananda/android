package com.fdananda.gitswipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
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
        recyclerView.setAdapter(adapter);
        swipe();

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

    public void swipe(){

        ItemTouchHelper.Callback itemTouch = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

                int dragFlags  = ItemTouchHelper.ACTION_STATE_IDLE; //Inativo
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                Toast.makeText(MainActivity.this, "Item completamente arrastado.\nIncluir ação aqui", Toast.LENGTH_SHORT).show();

            }
        };
        new ItemTouchHelper(itemTouch).attachToRecyclerView(recyclerView);
    }
}
