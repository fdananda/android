package com.example.git_componentes_de_interface;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextInputEditText textInputEditText;
    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2;
    private ToggleButton toggleButton;
    private Switch switch1;
    private CheckBox checkBox1, checkBox2;
    private TextView textView;
    String itensCapturados = "";
    String textoRadioGroup = "";
    String textoToggleButton = "";
    String textoSwitch = "";
    String textoCheckbox1 = "";
    String textoCheckbox2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText            = findViewById(R.id.editText);
        textInputEditText   = findViewById(R.id.textInputEditText);
        radioGroup          = findViewById(R.id.radioGroup);
        radioButton1        = findViewById(R.id.radioButton1);
        radioButton2        = findViewById(R.id.radioButton2);
        toggleButton        = findViewById(R.id.toggleButton);
        switch1             = findViewById(R.id.switch1);
        checkBox1           = findViewById(R.id.checkBox1);
        checkBox2           = findViewById(R.id.checkBox2);
        textView            =  findViewById(R.id.textView2);

        listenerButton1();
        listenerButton2();
        listenerToggle();
        listenerSwitch();
        listenerCheckbox1();
        listenerCheckbox2();
    }

    public void listenerButton1() {
        radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (radioButton1.isChecked()) {
                    Toast.makeText(MainActivity.this, "RadioButton1 foi selecionado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void listenerButton2() {
        radioButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (radioButton2.isChecked()) {
                    Toast.makeText(MainActivity.this, "RadioButton2 foi selecionado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void listenerToggle() {
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (toggleButton.isChecked()) {
                    Toast.makeText(MainActivity.this, "Toogle foi ligado", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Toogle foi desligado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void listenerSwitch() {
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switch1.isChecked()) {
                    Toast.makeText(MainActivity.this, "Switch foi ligado", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Switch foi desligado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void listenerCheckbox1() {
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox1.isChecked()) {
                    Toast.makeText(MainActivity.this, "Checkbox1 foi marcado", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Checkbox1 foi desmarcado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void listenerCheckbox2() {
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox2.isChecked()) {
                    Toast.makeText(MainActivity.this, "Checkbox2 foi marcado", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Checkbox2 foi desmarcado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void capturarDados(View view){

        //Seleção RadioGroup
        if(radioButton1.isChecked()){
            textoRadioGroup = "RadioButton1";
        }else if(radioButton2.isChecked()){
            textoRadioGroup = "RadioButton2";
        }else{
            textoRadioGroup = "Nenhum radioButton selecionado!";
        }

        //Seleção toggleButtorn
        if(toggleButton.isChecked()){
            textoToggleButton = "Ligado";
        }else{
            textoToggleButton = "Desligado";
        }

        //Seleção switch
        if(switch1.isChecked()){
            textoSwitch = "Ligado";
        }else{
            textoSwitch = "Desligado";
        }

        //Seleção checkbox1
        if(checkBox1.isChecked()){
            textoCheckbox1 = "Selecionado";
        }else{
            textoCheckbox1 = "Não selecionado";
        }

        //Seleção checkbox2
        if(checkBox2.isChecked()){
            textoCheckbox2 = "Selecionado";
        }else{
            textoCheckbox2 = "Não selecionado";
        }

        itensCapturados = "EditText: " + editText.getText().toString();
        itensCapturados = itensCapturados + "\nTextInputEditText: " + textInputEditText.getText().toString();
        itensCapturados = itensCapturados + "\nRadioGroup: " + textoRadioGroup;
        itensCapturados = itensCapturados + "\nToggleButton: " + textoToggleButton;
        itensCapturados = itensCapturados + "\nSwitch: " + textoSwitch;
        itensCapturados = itensCapturados + "\nCheckBox1: " + textoCheckbox1;
        itensCapturados = itensCapturados + "\nCheckBox2: " + textoCheckbox2;

        textView.setText(itensCapturados);
    }
}
