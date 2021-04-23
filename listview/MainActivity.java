package com.fdananda.gitlistview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listViewOpcoes;

    private String[] opcoes = {
            "Opção 1", "Opção 2", "Opção 3", "Opção 4", "Opção 5",
            "Opção 6", "Opção 7", "Opção 8", "Opção 9", "Opção 10",
            "Opção 11", "Opção 12", "Opção 13", "Opção 14", "Opção 15",
            "Opção 16", "Opção 17", "Opção 18", "Opção 19", "Opção 20"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewOpcoes  = findViewById(R.id.listView);

        //Passo 1 - Criar um adaptador
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), //Context
                R.layout.adapter, //Layout que será utilizado
                R.id.textViewAtributo1, //item dentro do layout que será preenchido com a lista
                opcoes //lista
        );

        //Passo 2 - Configurar o adapatador na lista
        listViewOpcoes.setAdapter(adapter);

        //Passo 3 - Adicionar evento de clique ao item da lista
        listViewOpcoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String opcaoSelecionda = listViewOpcoes.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), opcaoSelecionda, Toast.LENGTH_LONG).show();
            }
        });
    }
}
