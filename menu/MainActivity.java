package com.fdananda.gitmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textViewOpcaoSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Apresenta o menu na Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Trata a seleção dos itens da Toolbar

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        textViewOpcaoSelecionada  = findViewById(R.id.textViewOpcaoSelecionada);

        switch (item.getItemId()){
            case R.id.toast1:
                textViewOpcaoSelecionada.setText("Selecionada a opção Toast 1");
                Toast.makeText(this, "Selecionada a opção Toast 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toast2:
                textViewOpcaoSelecionada.setText("Selecionada a opção Toast 2");
                Toast.makeText(this, "Selecionada a opção Toast 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toast3:
                textViewOpcaoSelecionada.setText("Selecionada a opção Toast 3");
                Toast.makeText(this, "Selecionada a opção Toast 3", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
