package com.fdananda.gitmvvmsubtextviewbotao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView texto, texto2;
    private EditText novoTexto, novoTexto2;
    private Button buttonSubstituir;
    private MainActivityVM mainActivityVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto       = findViewById(R.id.textViewTexto);
        texto2       = findViewById(R.id.textViewTexto2);
        novoTexto   = findViewById(R.id.editTextNovoTexto);
        novoTexto2   = findViewById(R.id.editTextNovoTexto2);
        buttonSubstituir = findViewById(R.id.buttonSubstituirTexto);
        mainActivityVM = new ViewModelProvider(this).get(MainActivityVM.class);

        ativarBotao();
    }

    public void ativarBotao(){
        novoTexto.addTextChangedListener(MaskUtil.insert(novoTexto));
        novoTexto.addTextChangedListener(habilitarBotao);
        novoTexto2.addTextChangedListener(habilitarBotao);
        mainActivityVM.ativarBotao().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                buttonSubstituir.setEnabled(aBoolean);
            }
        });
    }

    private TextWatcher habilitarBotao = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String texto1 = novoTexto.getText().toString();
            String texto2 = novoTexto2.getText().toString();

            if (!texto1.isEmpty() && !texto2.isEmpty()){
                mainActivityVM.contarTexto(texto1, texto2);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    public void substituirTexto(View view){
        
        String textoRecuperado = novoTexto.getText().toString();

        if(textoRecuperado == null || textoRecuperado.isEmpty() || textoRecuperado == ""){
            Toast.makeText(this, "Preencha o campo!", Toast.LENGTH_SHORT).show();

        }else{
            mainActivityVM.enviarTexto(novoTexto.getText().toString(), novoTexto2.getText().toString());

            mainActivityVM.retornarTexto().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    texto.setText(s);
                }
            });

            mainActivityVM.retornarTexto2().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    texto2.setText(s);
                }
            });
        }
    }
}
