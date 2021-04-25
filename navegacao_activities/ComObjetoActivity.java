package com.fdananda.gitnavegacao;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ComObjetoActivity extends AppCompatActivity {

    private TextView textViewAtributoTextoObjeto;
    private TextView textViewAtributoNumeroObjeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_objeto);

        textViewAtributoTextoObjeto = findViewById(R.id.textViewTextoObjeto);
        textViewAtributoNumeroObjeto = findViewById(R.id.textViewNumeroObjeto);

        Bundle dadosRecebidos = getIntent().getExtras();

        Objeto objeto = (Objeto) dadosRecebidos.getSerializable("objeto");
        String textoAtributoTexto = objeto.getAtributoObjetoTexto();
        Integer textoAtributoNumero = objeto.getAtributoObjetoNumero();

        textViewAtributoTextoObjeto.setText(textoAtributoTexto);
        textViewAtributoNumeroObjeto.setText(String.valueOf(textoAtributoNumero));
    }

    public void voltarMainActivity(View view) {
        finish();
    }
}
