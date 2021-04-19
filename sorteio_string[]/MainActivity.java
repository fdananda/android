package com.fdananda.gitsorteiodelista;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView texto;
    String[] opcoes = {"Opção 1", "Opção 2", "Opção 3", "Opção 4", "Opção 5",
            "Opção 6", "Opção 7", "Opção 8", "Opção 9", "Opção 10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sortear(View view){
        texto               = findViewById(R.id.textViewTexto);
        int numeroSorteado  = new Random().nextInt(10);
        texto.setText(opcoes[numeroSorteado]);
    }
}
