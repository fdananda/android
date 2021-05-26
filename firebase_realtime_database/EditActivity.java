package com.fdananda.gitfirebaserealtimedatabase;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditarActivity extends AppCompatActivity {

    private EditText editTextAtributo1, editTextAtributo2, editTextAtributo3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        Bundle dadosRecebidos = getIntent().getExtras();

        editTextAtributo1 = findViewById(R.id.editTextAtributo1Editar);
        editTextAtributo2 = findViewById(R.id.editTextAtributo2Editar);
        editTextAtributo3 = findViewById(R.id.editTextAtributo3Editar);

        editTextAtributo1.setText(dadosRecebidos.getString("atributo1"));
        editTextAtributo2.setText(dadosRecebidos.getString("atributo2"));
        editTextAtributo3.setText(dadosRecebidos.getString("atributo3"));

    }

    public void atualizar(View view){

            String atributo1 = editTextAtributo1.getText().toString();
            String atributo2 = editTextAtributo2.getText().toString();
            String atributo3 = editTextAtributo3.getText().toString();

            if(atributo1.isEmpty() || atributo1 == ""){
                Toast.makeText(this, "Preencha um Texto válido!", Toast.LENGTH_SHORT).show();
            }else{
                if(atributo2.isEmpty() || atributo1 == ""){
                    Toast.makeText(this, "Preencha um Texto válido!", Toast.LENGTH_SHORT).show();
                }else{
                    if(atributo3.isEmpty() || atributo1 == ""){
                        Toast.makeText(this, "Preencha um Texto válido!", Toast.LENGTH_SHORT).show();
                    }else{

                        Bundle dadosRecebidos = getIntent().getExtras();

                        Atributo atributo = new Atributo();
                        atributo.setIdentificador(dadosRecebidos.getString("identificador"));
                        atributo.setAtributo1(atributo1);
                        atributo.setAtributo2(atributo2);
                        atributo.setAtributo3(atributo3);

                        atributo.editar();

                        Toast.makeText(this, "Dados alterados com sucesso!", Toast.LENGTH_SHORT).show();

                        editTextAtributo1.setText("");
                        editTextAtributo2.setText("");
                        editTextAtributo3.setText("");

                        finish();
                    }
                }
            }
        }

    public void cancelar(View view){
        finish();
    }
}
