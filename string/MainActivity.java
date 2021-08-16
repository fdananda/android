package com.fdananda.gitstring;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    
    private EditText editTextName;
    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        editTextName        = findViewById(R.id.editTextName);
        textViewResultado   = findViewById(R.id.textViewResult);
    }
    
    public void getName(View view){
        if (!editTextName.getText().toString().isEmpty() || !editTextName.getText().toString().equals("") ){

            //Nome completo
            String fullName = editTextName.getText().toString();

            //Quantidade de nomes
            String[] array = fullName.split(" ");
            int qtdNames = array.length;

            //Inicias
            String initials = "";
            for (int i = 0; i<qtdNames; i++){
                initials += String.valueOf(array[i].charAt(0));
            }

            //Primeiro e último nome
            String nameSurname = null;
            if (array.length>1){
                nameSurname = String.valueOf(array[0]) + " " + String.valueOf(array[(array.length-1)]);
            }else {
                nameSurname = String.valueOf(array[0]);
            }
            String resultado = "Nome completo: " + fullName +
                    "\nQuantidade de nomes: " + qtdNames +
                    "\nIniciais: " + initials +
                    "\nPrimeiro Nome e último sobrenome: " + nameSurname;

            //Desmembramento de nomes
            String[] name = new String[qtdNames] ;
            String names;
            for (int i = 0; i<qtdNames; i++){
               name[i]  = String.valueOf(array[i]);
               names = "Nome " + (i+1) + ": " + name[i];

               resultado = resultado + "\n" + names;
            }
            textViewResultado.setTextColor(getResources().getColor(R.color.black));
            textViewResultado.setText(resultado);

        }else {
           textViewResultado.setText("Campo obrigatório não preenchido!");
           textViewResultado.setTextColor(getResources().getColor(R.color.design_default_color_error));
        }
    }
}
