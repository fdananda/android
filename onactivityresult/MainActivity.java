package com.fdananda.gitonactivityresult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextNumber1, mEditTextNumber2;
    private TextView mTextViewResult;
    private static final int CALCULATE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextNumber1 = findViewById(R.id.edittext_number1);
        mEditTextNumber2 = findViewById(R.id.edittext_number2);
        mTextViewResult  = findViewById(R.id.textView);
    }

    public void openActivity2(View view){
        if (!mEditTextNumber1.getText().toString().equals("") && !mEditTextNumber2.getText().toString().equals("")){

            int number1 = Integer.parseInt(mEditTextNumber1.getText().toString());
            int number2 = Integer.parseInt(mEditTextNumber2.getText().toString());

            Intent intent = new Intent(this, MainActivity2.class);
            intent.putExtra("number1", number1);
            intent.putExtra("number2", number2);
            startActivityForResult(intent, CALCULATE_REQUEST_CODE);

        }else {
            mTextViewResult.setText("Campo obrigatório não preenchido");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CALCULATE_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                String operacao = data.getStringExtra("operacao");
                int result = data.getIntExtra("result", 0);
                mTextViewResult.setText(operacao + String.valueOf(result));
            }else if (resultCode == RESULT_CANCELED){
                mTextViewResult.setText("Nenhuma operação selecionada");
            }
        }
    }
}
