package com.fdananda.gitdata;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewDataAtual, textViewHoraAtual, textViewDetalhesDataHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDataAtual        = findViewById(R.id.textViewTextoData);
        textViewHoraAtual        = findViewById(R.id.textViewTextoHora);
        textViewDetalhesDataHora = findViewById(R.id.textViewDetalhesDataHora);

    }

    public void obterDataAtual(View view){
        textViewDataAtual.setText(Data.getDataAtual());
    }

    public void obterHoraAtual(View view){
        textViewHoraAtual.setText(Data.getHoraAtual());
    }

    public void obterDetalhesDataHora(View view){
        textViewDetalhesDataHora.setText(Data.getDetalhesDataHora());
    }
}
