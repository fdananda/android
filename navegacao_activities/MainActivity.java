package com.fdananda.gitnavegacao;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Componentes passagem de dados
    private EditText editTextAtributoTexto;
    private EditText editTextAtributoNumero;

    //Componentes passagem de objetos
    private EditText editTextAtributoTextoObjeto;
    private EditText editTextAtributoNumeroObjeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Componentes passagem de dados
        editTextAtributoTexto    = findViewById(R.id.editTextAtributoTexto);
        editTextAtributoNumero   = findViewById(R.id.editTextAtributoNumero);

        //Componentes passagem de objetos
        editTextAtributoTextoObjeto     = findViewById(R.id.editTextAtributoTextoObjeto);
        editTextAtributoNumeroObjeto    = findViewById(R.id.editTextAtributoNumeroObjeto);
    }

    public void abrirActivitySimples(View view){
        Intent intent = new Intent(this, SimplesActivity.class);
        startActivity(intent);
    }

    public void abrirActivityComDados(View view){

        String atributoTexto    = editTextAtributoTexto.getText().toString();
        String textoNumero      = editTextAtributoNumero.getText().toString();

        if(!atributoTexto.isEmpty()){
            if(!textoNumero.isEmpty()){

                try {

                    Integer atributoNumero  = Integer.parseInt(textoNumero);

                    Intent intent = new Intent(getApplicationContext(), ComDadosActivity.class);
                    intent.putExtra("Texto", atributoTexto);
                    intent.putExtra("Numero", atributoNumero);
                    startActivity(intent);

                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Preencha um número válido!",
                            Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(getApplicationContext(),
                        "Preencha o número!",
                        Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),
                    "Preencha o texto",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void abrirActivityComObjeto(View view){

        String atributoTexto    = editTextAtributoTextoObjeto.getText().toString();
        String textoNumero      = editTextAtributoNumeroObjeto.getText().toString();

        if(!atributoTexto.isEmpty()){
            if(!textoNumero.isEmpty()){

                try {

                    Integer atributoNumero  = Integer.parseInt(textoNumero);
                    Objeto objeto = new Objeto(atributoTexto, atributoNumero);

                    Intent intent = new Intent(getApplicationContext(), ComDadosActivity.class);
                    intent.putExtra("Texto", atributoTexto);
                    intent.putExtra("Numero", atributoNumero);

                    intent.putExtra("objeto", objeto);
                    startActivity(intent);

                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Preencha um número válido!",
                            Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(getApplicationContext(),
                        "Preencha o número!",
                        Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),
                    "Preencha o texto",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
