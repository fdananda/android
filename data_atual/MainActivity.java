package com.fdananda.gitdata;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView textViewDataAtual, textViewHoraAtual, textViewDetalhesDataHora;
    private EditText editTextQtdDias, editTextDataInicial, editTextDataFinal;
    private Switch switchType;
    private TextView textViewResultado, textViewResultado2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDataAtual        = findViewById(R.id.textViewTextoData);
        textViewHoraAtual        = findViewById(R.id.textViewTextoHora);
        textViewDetalhesDataHora = findViewById(R.id.textViewDetalhesDataHora);

        editTextQtdDias          = findViewById(R.id.editTextQuant);
        switchType               = findViewById(R.id.switch1);
        textViewResultado        = findViewById(R.id.textViewResultado);

        editTextDataInicial      = findViewById(R.id.editText_data1);
        editTextDataFinal        = findViewById(R.id.editText_data2);
        textViewResultado2       = findViewById(R.id.textViewResultado2);

        //Incluir máscaras nas datas
        editTextDataInicial.addTextChangedListener(mask(editTextDataInicial));
        editTextDataFinal.addTextChangedListener(mask(editTextDataFinal));

    }

    public void obterDataAtual(View view){
        textViewDataAtual.setText(Data.getDataAtual());
    }

    public void obterHoraAtual(View view){
        textViewHoraAtual.setText(Data.getHoraAtual());
    }

    public void obterDetalhesDataHora(View view){ textViewDetalhesDataHora.setText(Data.getDetalhesDataHora()); }

    public void calcularDatas(View view){
        if (!editTextQtdDias.getText().toString().equals("") || !editTextQtdDias.getText().toString().isEmpty()){

            long dataAtual = System.currentTimeMillis();
            Date novaData  = new Date(dataAtual);

            Boolean type;
            type = switchType.isChecked();
            Log.d("type", type.toString());
            if (!type){
                novaData.setDate(novaData.getDate()-Integer.parseInt(editTextQtdDias.getText().toString()));
            }else {
                novaData.setDate(novaData.getDate()+Integer.parseInt(editTextQtdDias.getText().toString()));
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dataResultado = simpleDateFormat.format(novaData);

            textViewResultado.setText(dataResultado);
        }else {
            textViewResultado.setText("");
            Toast.makeText(this, "Preencha a quantidade de dias!", Toast.LENGTH_SHORT).show();
        }
    }

    public void calcularDias(View view){

        if ((!editTextDataInicial.getText().toString().equals("") || !editTextDataInicial.getText()
                .toString().isEmpty()) && (!editTextDataFinal.getText().toString().equals("") ||
                !editTextDataFinal.getText().toString().isEmpty())){

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            Boolean dateOk;

            try {
                dateFormat.parse(editTextDataInicial.getText().toString());
                dateOk = true;
            } catch (ParseException ex) {
                dateOk = false;
                Toast.makeText(this, "Data inicial inválida", Toast.LENGTH_SHORT).show();
            }
            
            if (dateOk){
                try {
                    dateFormat.parse(editTextDataFinal.getText().toString());
                    dateOk = true;
                } catch (ParseException ex) {
                    dateOk = false;
                    Toast.makeText(this, "Data final inválida", Toast.LENGTH_SHORT).show();
                }
            }
            
            if(dateOk){
                try {
                    Date dataInicial = dateFormat.parse(editTextDataInicial.getText().toString());
                    Date dataFinal   = dateFormat.parse(editTextDataFinal.getText().toString());
                    
                    if (dataFinal.getTime()>dataInicial.getTime()){
                        long qtdDias = (dataFinal.getTime() - dataInicial.getTime()) + 3600000;
                        textViewResultado2.setText(String.valueOf(qtdDias / 86400000L) + " dias");
                    }else {
                        textViewResultado2.setText("");
                        Toast.makeText(this, "Data Inicial maior do que Data Final. Favor corrigir", Toast.LENGTH_SHORT).show();
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }else {
            Toast.makeText(this, "Campo(s) não preenchido(s)", Toast.LENGTH_SHORT).show();
        }
    }

    public static TextWatcher mask(EditText editText) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            @Override
            public void afterTextChanged(final Editable s) {
            }

            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                final String str = unmask(s.toString());
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (final char m : "##/##/####".toCharArray()) {
                    if (m != '#' && str.length() > old.length()) {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (final Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                editText.setText(mascara);
                editText.setSelection(mascara.length());
            }

            public String unmask(final String s) {
                return s.replaceAll("[.]", "").replaceAll("[-]", "").replaceAll("[/]", "").replaceAll("[(]", "").replaceAll("[ ]","").replaceAll("[:]", "").replaceAll("[)]", "");
            }
        };
    }
}
