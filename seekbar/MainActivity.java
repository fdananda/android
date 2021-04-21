package com.fdananda.gitseekbar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView textoProgresso;
    private Button botaoProgresso;

    private SeekBar seekBarDiscrete;
    private TextView textoProgressoDiscrete;
    private Button botaoProgressoDiscrete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        textoProgresso = findViewById(R.id.textViewTexto);
        botaoProgresso = findViewById(R.id.buttonProgresso);

        seekBarDiscrete = findViewById(R.id.seekBarDiscrete);
        textoProgressoDiscrete = findViewById(R.id.textViewTextoProgressoDiscrete);
        botaoProgressoDiscrete = findViewById(R.id.buttonProgressoDiscrete);

        seekBar.setOnSeekBarChangeListener(new
               SeekBar.OnSeekBarChangeListener() {
                      @Override
                      public void onProgressChanged(SeekBar seekBar, int
                      progress, boolean fromUser) {
                          textoProgresso.setVisibility(View.VISIBLE);
                          textoProgresso.setText("Progresso: " + progress + " / " + seekBar.getMax());
                      }

                      @Override
                      public void onStartTrackingTouch(SeekBar seekBar) {

                      }

                      @Override
                      public void onStopTrackingTouch(SeekBar seekBar) {

                      }
               });

        botaoProgresso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView texto = new TextView(getApplicationContext());
                texto.setBackgroundResource(R.color.black);
                texto.setTextColor(getResources().getColor(R.color.white));
                texto.setPadding(25, 25, 25, 25);
                texto.setAllCaps(true);
                texto.setTextSize(20);

                texto.setText("Progresso recuperado: " + seekBar.getProgress());

                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                //toast.setView(imagem);
                toast.setView(texto);
                toast.setGravity(Gravity.BOTTOM, 0, 80);
                toast.show();
            }
        });

        seekBarDiscrete.setOnSeekBarChangeListener(new
             SeekBar.OnSeekBarChangeListener() {
                 @Override
                 public void onProgressChanged(SeekBar seekBar, int
                    progress, boolean fromUser) {
                    textoProgressoDiscrete.setVisibility(View.VISIBLE);
                    textoProgressoDiscrete.setText("Progresso: " + progress + " / " + seekBar.getMax());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
             });
    }

    public void recuperarProgressoDiscrete(View view){

        TextView texto = new TextView(getApplicationContext());
        texto.setBackgroundResource(R.color.black);
        texto.setTextColor(getResources().getColor(R.color.white));
        texto.setPadding(25, 25, 25, 25);
        texto.setAllCaps(true);
        texto.setTextSize(20);

        texto.setText("Progresso recuperado: " + seekBarDiscrete.getProgress());

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        //toast.setView(imagem);
        toast.setView(texto);
        toast.setGravity(Gravity.BOTTOM, 0, 80);
        toast.show();
    }
}
