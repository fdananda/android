package com.fdananda.git_botao_texto;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView texto;
    private EditText novoTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void substituirTexto(View view){
        texto       = findViewById(R.id.textViewTexto);
        novoTexto   = findViewById(R.id.editTextNovoTexto);

        texto.setText(novoTexto.getText().toString());
        novoTexto.setText("");
    }
}
