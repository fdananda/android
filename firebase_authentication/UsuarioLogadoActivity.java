package com.fdananda.gitfirebaseauthentication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UsuarioLogadoActivity extends AppCompatActivity {

    private TextView textViewDadosUsuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_logado);

        Bundle dadosRecebidos = getIntent().getExtras();

        textViewDadosUsuarioLogado = findViewById(R.id.textViewActivity2);
        textViewDadosUsuarioLogado.setText("E-mail: " + dadosRecebidos.getString("email") + "\nUid: " + dadosRecebidos.get("uid"));
    }

    public void voltar(View view){
        finish();
    }
}
