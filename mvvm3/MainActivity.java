package com.fdananda.gitmvvm3;

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

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private TextView mTextView;
    private Button   mButton;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.editTextText);
        mTextView = findViewById(R.id.textView);
        mButton   = findViewById(R.id.button);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!mEditText.getText().toString().isEmpty() || !mEditText.getText().equals("")){
                    viewModel.onText(mEditText.getText().toString());
                    mTextView.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        viewModel.getButtonStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mButton.setEnabled(aBoolean);
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResult();
            }
        });
    }

    private void getResult(){
        viewModel.getResultType().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTextView.setText(s);
            }
        });
    }
}
