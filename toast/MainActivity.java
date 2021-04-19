package com.fdananda.gittoast;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button  botaoToastSimplesLong, botaoToastSimplesShort,
                    botaoToastPersonalizado, botaoToastComLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirToastSimplesLong(View view){
        botaoToastSimplesLong   =   findViewById(R.id.buttonToastSimplesLong);
        Toast.makeText(getApplicationContext(),
                "Exemplo de Toast Simples do tipo Long",
                Toast.LENGTH_LONG
        ).show();
    }

    public void abrirToastSimplesShort(View view){
        botaoToastSimplesShort   =   findViewById(R.id.buttonToastSimplesShort);
        Toast.makeText(getApplicationContext(),
                "Exemplo de Toast Simples do tipo Short",
                Toast.LENGTH_SHORT
        ).show();
    }

    public void abrirToastPersonalizado(View view){
        botaoToastPersonalizado   =   findViewById(R.id.buttonToastPersonalizado);

        ImageView imagem = new ImageView(getApplicationContext());
        imagem.setImageResource(android.R.drawable.btn_star_big_on);

        TextView texto = new TextView(getApplicationContext());
        texto.setBackgroundResource(R.color.design_default_color_error);
        texto.setTextColor(getResources().getColor(R.color.white));
        texto.setPadding(50, 50, 50, 50);
        texto.setAllCaps(true);
        texto.setText("Exemplo de Toast Personalizado");

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        //toast.setView(imagem);
        toast.setView(texto);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    public void abrirToastComLayout(View view){
        botaoToastComLayout   =   findViewById(R.id.buttonToastComLayout);

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast, null);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
