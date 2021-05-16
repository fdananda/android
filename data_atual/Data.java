package com.fdananda.gitdata;

import java.text.SimpleDateFormat;

public class Data {

    public static String getDataAtual(){

        long data = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String dataAtual = "";
        dataAtual = simpleDateFormat.format(data);

        return dataAtual;
    }

    public static String getHoraAtual(){

        long data = System.currentTimeMillis();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        String horaAtual = "";
        horaAtual = simpleDateFormat.format(data);

        return horaAtual;
    }

    public static String getDetalhesDataHora(){

        String arrayData[] = getDataAtual().split("/");
        String dia = arrayData[0];
        String mes = arrayData[1];
        String ano = arrayData[2];

        String arrayHora[] = getHoraAtual().split(":");
        String hora     = arrayHora[0];
        String minutos  = arrayHora[1];
        String segundos = arrayHora[2];

        String detalhesHora = "Dia: " + dia + " - Mês: " + mes + " - Ano: " + ano +
                "\nHora: " + hora + " - Minutos: " + minutos + " - Segundos: " + segundos;

        return detalhesHora;
    }
}
