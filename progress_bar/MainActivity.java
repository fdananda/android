package com.fdananda.gitprogressbar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar barraProgressoHorizontal;
    private TextView textoProgressoHorizontal;
    private ProgressBar barraProgressoCircular;
    private TextView textoProgressoCircular;
    private int progresso = 0;
    private int progressoCircular = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoProgressoHorizontal = findViewById(R.id.textViewProgressoHorizontal);
        textoProgressoCircular = findViewById(R.id.textViewProgressoCircular);
        barraProgressoHorizontal = findViewById(R.id.progressBarHorizontal);
        barraProgressoCircular   = findViewById(R.id.progressBarCircular);
        barraProgressoHorizontal.setVisibility(View.GONE);
        textoProgressoHorizontal.setVisibility(View.GONE);
        barraProgressoCircular.setVisibility(View.GONE);
        textoProgressoCircular.setVisibility(View.GONE);
    }

    public void acionarProgressBarHorizontal(View view) {

        String textoProgresso;

        if (barraProgressoHorizontal.getProgress()!=100){
            barraProgressoHorizontal.setVisibility(View.VISIBLE);
            textoProgressoHorizontal.setVisibility(View.VISIBLE);
            barraProgressoHorizontal.setProgress(this.progresso);

            textoProgresso = String.valueOf(progresso);
            textoProgressoHorizontal.setText(textoProgresso);

            this.progresso = this.progresso + 25;
            textoProgressoHorizontal.setText(textoProgresso);

        }else {
            textoProgresso = "Progresso concluído.\nClique para reiniciar";
            textoProgressoHorizontal.setText(textoProgresso);
            barraProgressoHorizontal.setVisibility(View.GONE);
            this.progresso = 0;
            barraProgressoHorizontal.setProgress(this.progresso);
        }
    }

    public void acionarProgressBarCircular(View view) {

        String textoProgresso;

        if(progressoCircular>100){
            textoProgresso = "Progresso concluído.\nClique para reiniciar";
            textoProgressoCircular.setText(textoProgresso);
            barraProgressoCircular.setVisibility(View.GONE);
            this.progressoCircular = 0;
            barraProgressoCircular.setProgress(this.progressoCircular);
        }else {
            barraProgressoCircular.setVisibility(View.VISIBLE);
            textoProgressoCircular.setVisibility(View.VISIBLE);
            barraProgressoCircular.setProgress(this.progressoCircular);

            textoProgresso = String.valueOf(progressoCircular);
            textoProgressoCircular.setText(textoProgresso);

            this.progressoCircular = this.progressoCircular + 10;
            textoProgressoCircular.setText(textoProgresso);
        }
    }
}
