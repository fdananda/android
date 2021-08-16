package com.fdananda.gitinputmethodmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button buttonHideKeyboard, buttonShowKeyBoard;
    private EditText editText;
    private InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonHideKeyboard = findViewById(R.id.button_hideKeyboard);
        buttonShowKeyBoard = findViewById(R.id.button_showKeyboard);
        buttonShowKeyBoard.setVisibility(View.INVISIBLE);
        editText = findViewById(R.id.editText);
        inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                buttonShowKeyBoard.setVisibility(View.VISIBLE);
            }
        });

        buttonHideKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        buttonShowKeyBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputMethodManager.showSoftInput(editText, 0);
            }
        });
    }
}
