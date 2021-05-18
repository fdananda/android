package com.fdananda.gitdecimalformat;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumeroDigitado;
    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResultado      = findViewById(R.id.textViewResultado);
        editTextNumeroDigitado = findViewById(R.id.editTextNumeroDigitado);

    }

    public void somarFormatar(View view){

        String numeroDigitado = editTextNumeroDigitado.getText().toString();
        String numeroDigitadoFormatado = numeroDigitado.replaceAll(",", ".");

        if(numeroDigitado.isEmpty() || numeroDigitado == ""){
            textViewResultado.setText("Digite um número válido");
        }else{

            Double somaSemFormatacao = Double.valueOf(numeroDigitadoFormatado).doubleValue() + 3.14159265359;
            String resultadoSemFormatacao = somaSemFormatacao.toString();

            DecimalFormat decimalFormat2numeros = new DecimalFormat("0.##");
            DecimalFormat decimalFormat2numerosComZero = new DecimalFormat("0.00");
            String resultado2numeros = decimalFormat2numeros.format(somaSemFormatacao);
            String resultado2numerosComZero = decimalFormat2numerosComZero.format(somaSemFormatacao);

            DecimalFormat decimalFormat3numeros = new DecimalFormat("0.000");
            String resultado3numeros = decimalFormat3numeros.format(somaSemFormatacao);

            DecimalFormat decimalFormat4numeros = new DecimalFormat("0.0000");
            String resultado4numeros = decimalFormat4numeros.format(somaSemFormatacao);

            String resultado = "Soma sem formatação: " + resultadoSemFormatacao +
                    "\nSoma com 2 casas decimais sem 0: " + resultado2numeros +
                    "\nSoma com 2 casas decimais com 0: " + resultado2numerosComZero +
                    "\nSoma com 3 casas decimais com 0: " + resultado3numeros +
                    "\nSoma com 4 casas decimais com 0: " + resultado4numeros;
            textViewResultado.setText(resultado);

        }
    }
}
