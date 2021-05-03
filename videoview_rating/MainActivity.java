package com.fdananda.gitvideoview;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView    textViewEstrelas;
    private RatingBar   ratingBarEstrelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratingBarEstrelas = findViewById(R.id.ratingBar);
        textViewEstrelas  = findViewById(R.id.textViewNotasEstrelas);

        ratingBarEstrelas.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                int numeroEstrelas = (int) rating;
                String textoAvaliacao = null;

                switch (numeroEstrelas){
                    case 1:
                        textoAvaliacao = "1 Estrela";
                        break;
                    case 2:
                        textoAvaliacao = "2 Estrelas";
                        break;
                    case 3:
                        textoAvaliacao = "3 Estrelas";
                        break;
                    case 4:
                        textoAvaliacao = "4 Estrelas";
                        break;
                    case 5:
                        textoAvaliacao = "5 Estrelas";
                        break;
                }
                textViewEstrelas.setText(textoAvaliacao);
            }
        });
    }

    public void abrirVideo(View view){

        Intent intent = new Intent(getApplicationContext(), VideoActivity.class);
        startActivity(intent);
    }
}
