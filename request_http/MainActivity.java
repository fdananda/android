package com.fdananda.gitrequesthttp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResponse;
    String textoFormatado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResponse = findViewById(R.id.textViewResponse);
    }

    public void requestHTTP(View view){

        String url = "https://danpnobre.com.br/wp-json/wp/v2/posts/1396";
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(url);
    }

    //<Parâmetro de entrada, Progresso, Retorno>
    public class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            String urlRecebida = strings[0];
            InputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            BufferedReader bufferedReader = null;
            StringBuffer stringBuffer = null;

            try {
                URL urlApi = new URL(urlRecebida);

                //Conectar
                HttpURLConnection httpURLConnection = (HttpURLConnection) urlApi.openConnection();

                //Retorno em bytes
                inputStream = httpURLConnection.getInputStream();

                //Conversão de bytes para caracteres
                inputStreamReader = new InputStreamReader(inputStream);

                //Conversão de caracteres para String
                bufferedReader = new BufferedReader(inputStreamReader);

                //Leitura linha a linha
                String linhaBuffered = "";
                stringBuffer = new StringBuffer();

                while ((linhaBuffered = bufferedReader.readLine()) != null){
                    stringBuffer.append(linhaBuffered);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
                textoFormatado = "Erro: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                textoFormatado = "Erro: " + e.getMessage();
            }

            if (stringBuffer != null){
                return stringBuffer.toString();
            }else{
                return textoFormatado;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            int id = 0;
            String objetoTitulo = null;
            String titulo = null;
            String data = null;
            String link = null;
            String objetoTexto = null;
            String texto = null;

            try {
                JSONObject jsonObject = new JSONObject(s);

                textoFormatado = s;

                    id      = jsonObject.getInt("id");
                    data    = jsonObject.getString("date");
                    link    = jsonObject.getString("link");

                    objetoTitulo  = jsonObject.getString("title");
                    JSONObject jsonObjectTitulo = new JSONObject(objetoTitulo);
                    titulo        = jsonObjectTitulo.getString("rendered");

                    objetoTexto = jsonObject.getString("content");
                    JSONObject jsonObjectTexto = new JSONObject(objetoTexto);
                    texto       = jsonObjectTexto.getString("rendered");

                    textoFormatado = "ID: " + String.valueOf(id) + "\n\nTítulo: " + titulo + "\n\nData: " + data +
                            "\n\nLink: " + link + "\n\nTexto: " + texto;

            } catch (JSONException e) {
                e.printStackTrace();
                textoFormatado = "Erro: " +e.getMessage();
            }

            textViewResponse.setText(textoFormatado);
        }
    }
}
