package com.fdananda.gitsearchview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView   = findViewById(R.id.searchView);

        searchView.setQueryHint("Digite o termo a ser pesquisado");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("texto enviado", "Texto enviado: " + query);
                String textoPesquisa = query.toLowerCase();
                Log.d("texto enviado", "Texto a ser pesquisado em caixa baixa: " + textoPesquisa);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("texto modificado", "Texto modificado: " + newText);
                return true;
            }
        });
    }
}
