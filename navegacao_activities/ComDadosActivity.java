package com.fdananda.gitnavegacao;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ComDadosActivity extends AppCompatActivity {

    private TextView textViewAtributoTexto;
    private TextView textViewAtributoNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_dados);

        textViewAtributoTexto   = findViewById(R.id.textViewTexto);
        textViewAtributoNumero   = findViewById(R.id.textViewNumero);

        Bundle dadosRecebidos = getIntent().getExtras();
        String textoAtributoTexto = dadosRecebidos.getString("Texto");
        Integer textoAtributoNumero = dadosRecebidos.getInt("Numero");

        textViewAtributoTexto.setText(textoAtributoTexto);
        textViewAtributoNumero.setText(String.valueOf(textoAtributoNumero));
    }

    public void voltarMainActivity(View view){
        finish();
    }
}
