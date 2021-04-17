package com.example.git_sorteio;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sortear(View view){
        texto               = findViewById(R.id.textViewTexto);
        int numeroSorteado  = new Random().nextInt(1001);
        texto.setText(String.valueOf(numeroSorteado));
    }
}
