package com.fdananda.gitradiobutton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    private MainActivityViewModel viewModel;
    private TextView textView;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioButton1 = findViewById(R.id.radioButtonOption1);
        radioButton2 = findViewById(R.id.radioButtonOption2);
        radioButton3 = findViewById(R.id.radioButtonOption3);
        radioButton4 = findViewById(R.id.radioButtonOption4);
        textView     = findViewById(R.id.textViewResult);
        radioGroup = findViewById(R.id.radioGroup);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                String optionSelected = "";
                if (checkedId == radioButton1.getId()){
                    optionSelected = "1";
                }else if(checkedId == radioButton2.getId()){
                    optionSelected = "2";
                }else if(checkedId == radioButton3.getId()){
                    optionSelected = "3";
                }else if(checkedId == radioButton4.getId()){
                    optionSelected = "4";
                }
                viewModel.onChoice(optionSelected);
            }
        });

        viewModel.getOptionSelected().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                radioButton1.setBackgroundColor(getResources().getColor(R.color.background));
                radioButton2.setBackgroundColor(getResources().getColor(R.color.background));
                radioButton3.setBackgroundColor(getResources().getColor(R.color.background));
                radioButton4.setBackgroundColor(getResources().getColor(R.color.background));
                textView.setText("Opção escolhida: " + s);

                switch (s){
                    case "1":
                        radioButton1.setBackgroundColor(getResources().getColor(R.color.background_selected));
                        break;
                    case "2":
                        radioButton2.setBackgroundColor(getResources().getColor(R.color.background_selected));
                        break;
                    case "3":
                        radioButton3.setBackgroundColor(getResources().getColor(R.color.background_selected));
                        break;
                    case "4":
                        radioButton4.setBackgroundColor(getResources().getColor(R.color.background_selected));
                        break;
                }
            }
        });
    }
}
