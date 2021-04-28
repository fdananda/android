package com.fdananda.gitsnackbar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirSnackbar(View view){
        Snackbar.make(view, "Texto da SnackBar", Snackbar.LENGTH_INDEFINITE)
                .setAction("Confirmar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Ação confirmada!", Toast.LENGTH_SHORT).show();
                    }
                }).setActionTextColor(view.getResources().getColor(R.color.white))
                .setBackgroundTint(view.getResources().getColor(R.color.design_default_color_primary))
                .show();
    }
}
