package com.fdananda.gitlistview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewOpcoes;
    private ArrayList<Objeto> criarObjetos(){
        ArrayList<Objeto> objetos = new ArrayList<Objeto>();

        Objeto objeto1 = new Objeto("Título 1", "Descrição do título 1");
        objetos.add(objeto1);

        Objeto objeto2 = new Objeto("Título 2", "Descrição do título 2");
        objetos.add(objeto2);

        Objeto objeto3 = new Objeto("Título 3", "Descrição do título 3");
        objetos.add(objeto3);

        Objeto objeto4 = new Objeto("Título 4", "Descrição do título 4");
        objetos.add(objeto4);

        Objeto objeto5 = new Objeto("Título 5", "Descrição do título 5");
        objetos.add(objeto5);

        Objeto objeto6 = new Objeto("Título 6", "Descrição do título 6");
        objetos.add(objeto6);

        Objeto objeto7 = new Objeto("Título 7", "Descrição do título 7");
        objetos.add(objeto7);

        return objetos;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewOpcoes  = findViewById(R.id.listView);

        ArrayList<Objeto> objetos = criarObjetos();
        ArrayAdapter adapter = new AdapterCustomizado(this, objetos);
        listViewOpcoes.setAdapter(adapter);

        listViewOpcoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String opcaoSelecionada = objetos.get(position).getAtributo1() + "\n" +  objetos.get(position).getAtributo2() ;
                Toast.makeText(getApplicationContext(), opcaoSelecionada, Toast.LENGTH_LONG).show();
            }
        });
    }
}
