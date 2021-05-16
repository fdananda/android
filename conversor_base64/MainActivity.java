package com.fdananda.gitbase64conversor;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTextoCodificar;
    private EditText editTextTextoDecodificar;
    private TextView textViewTextoCodificado;
    private TextView textViewTextoDecodificado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTextoCodificar    = findViewById(R.id.editTextTextoCodificar);
        editTextTextoDecodificar  = findViewById(R.id.editTextTextoDecodificar);
        textViewTextoCodificado   = findViewById(R.id.textViewRetornoCodificar);
        textViewTextoDecodificado   = findViewById(R.id.textViewRetornoDecodificar);
    }

    public void codificar(View view){

        String textoACodificar = editTextTextoCodificar.getText().toString();
        String textoCodificado = ConversorBase64.codificar(textoACodificar);

        textViewTextoCodificado.setText(textoCodificado);
    }

    public void decodificar(View view){

        String textoADecodificar = editTextTextoDecodificar.getText().toString();
        String textoDecodificado = ConversorBase64.decodificar(textoADecodificar);

        textViewTextoDecodificado.setText(textoDecodificado);
    }
    
}
