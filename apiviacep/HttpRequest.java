package com.fdananda.gitapiviacep.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {

    private String url;

    public HttpRequest(String url) {
        this.url = url;
    }

    public String getCepInfo(){

        String resposta = "";
        String url = this.url;

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            int status = urlConnection.getResponseCode();

            if (status == 200){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

                String line = null;
                StringBuilder stringBuilder = new StringBuilder();

                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);
                }

                bufferedReader.close();
                resposta = stringBuilder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return resposta;
    }
}
