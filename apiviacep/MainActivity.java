package com.fdananda.gitapiviacep;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.fdananda.gitapiviacep.model.Endereco;
import com.fdananda.gitapiviacep.util.HttpRequest;
import com.fdananda.gitapiviacep.util.ResponseParser;

public class MainActivity extends AppCompatActivity {

    private EditText editTextCep;
    private TextView textViewResultado;
    Endereco endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCep         = findViewById(R.id.editTextCep);
        textViewResultado   = findViewById(R.id.textViewResultado);
        editTextCep.addTextChangedListener(mask(editTextCep));
    }

    public void getCep(View view){

        if (!editTextCep.getText().toString().isEmpty() || !editTextCep.getText().toString().equals("")){

            String cep = editTextCep.getText().toString()
                    .replaceAll("[-]", "")
                    .replaceAll("[ ]","");

            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            MyAsyncTask myAsyncTask = new MyAsyncTask();
            myAsyncTask.execute(url);
        }
    }

    //<Entrada, Progresso, Retorno>
    public class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            String url = strings[0];
            HttpRequest httpRequest = new HttpRequest(url);
            String response = httpRequest.getCepInfo();
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ResponseParser responseParser = new ResponseParser(s);
            endereco = responseParser.parserEndereco();
            if (!endereco.getCep().equals("")){
                String resultado = "CEP: " + endereco.getCep() + "\nLogradouro: " + endereco.getLogradouro()
                        + "\nComplemento: " + endereco.getComplemento() + "\nBairro: " + endereco.getBairro()
                        + "\nCidade: " + endereco.getLocalidade() + "\nUF: " + endereco.getUf();
                textViewResultado.setText(resultado);
            }else {
                textViewResultado.setText("CEP não localizado");
            }
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
                for (final char m : "#####-###".toCharArray()) {
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
