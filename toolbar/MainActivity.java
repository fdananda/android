package com.fdananda.gittoolbar;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Git Toolbar");
        toolbar.setSubtitle("Subtítulo Toolbar Personalizada");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.design_default_color_background));
        toolbar.setTitleMarginBottom(5);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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
            case R.id.activityToolbar2:
                Intent intent = new Intent(this, ToolbarPersonalizadaActivity.class);
                startActivity(intent);
                break;
            case R.id.sair:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
