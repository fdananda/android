package com.fdananda.gittextwatcher;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText_text);
        textView = findViewById(R.id.textView_Text);
        textView.setText("O texto que você digitar, aparecerá aqui");

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    textView.setText(editText.getText().toString());
                }else {
                    textView.setText("O texto que você digitar, aparecerá aqui.\nTente de novo!");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
