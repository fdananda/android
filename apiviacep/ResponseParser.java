package com.fdananda.gitapiviacep.util;

import com.fdananda.gitapiviacep.model.Endereco;
import org.json.JSONException;
import org.json.JSONObject;

public class ResponseParser {

    private String response;

    public ResponseParser(String response) {this.response = response; }

    public Endereco parserEndereco(){

        Endereco endereco = null;
        try {
            JSONObject jsonObject = new JSONObject(this.response);

            String cep = jsonObject.getString("cep");
            String logradouro = jsonObject.getString("logradouro");
            String complemento = jsonObject.getString("complemento");
            String bairro = jsonObject.getString("bairro");
            String localidade = jsonObject.getString("localidade");
            String uf = jsonObject.getString("uf");
            String ibge = jsonObject.getString("ibge");
            String gia = jsonObject.getString("gia");
            String ddd = jsonObject.getString("ddd");
            String siafi = jsonObject.getString("siafi");

            endereco = new Endereco(cep, logradouro, complemento, bairro, localidade, uf, ibge, gia, ddd, siafi);

        } catch (JSONException e) {
            e.printStackTrace();
            endereco = new Endereco("", "", "", "", "", "", "", "", "", "");
        }
        return endereco;
    }
}
