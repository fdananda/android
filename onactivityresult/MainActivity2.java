package com.fdananda.gitonactivityresult;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    private EditText editTextNumber1, editTextNumber2;
    private Intent intentResult;
    private int number1, number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        intentResult = new Intent();
        editTextNumber1 = findViewById(R.id.edittext_number1_a2);
        editTextNumber2 = findViewById(R.id.edittext_number2_a2);

        Intent intent = getIntent();
        number1 = intent.getIntExtra("number1", 0);
        number2 = intent.getIntExtra("number2", 0);

        editTextNumber1.setText(String.valueOf(number1));
        editTextNumber2.setText(String.valueOf(number2));
    }

    public void add(View view){
        int result = number1 + number2;
        intentResult.putExtra("operacao", "Soma: ");
        intentResult.putExtra("result", result);
        setResult(RESULT_OK, intentResult);
        finish();
    }

    public void minus(View view){
        int result = number1 - number2;
        intentResult.putExtra("operacao", "Subtração: ");
        intentResult.putExtra("result", result);
        setResult(RESULT_OK, intentResult);
        finish();
    }
    public void multiply(View view){
        int result = number1 * number2;
        intentResult.putExtra("operacao", "Multiplicação: ");
        intentResult.putExtra("result", result);
        setResult(RESULT_OK, intentResult);
        finish();
    }

    public void divide(View view){
        int result = number1 / number2;
        intentResult.putExtra("operacao", "Divisão: ");
        intentResult.putExtra("result", result);
        setResult(RESULT_OK, intentResult);
        finish();
    }
}
