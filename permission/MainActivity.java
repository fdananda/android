package com.fdananda.gitpermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResultadoPermissao;
    private String[] permissoes = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResultadoPermissao = findViewById(R.id.textViewResultadoPermissao);
        Permissao.concederPermissao(permissoes, this, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int permissaoResultado : grantResults){

            if (permissaoResultado == PackageManager.PERMISSION_DENIED){
                textViewResultadoPermissao.setText("Permissão foi negada. Não é possível usar o app!");
                textViewResultadoPermissao.setTextColor(getResources().getColor(R.color.design_default_color_error));
                textViewResultadoPermissao.setGravity(Gravity.CENTER);
            }else{
                textViewResultadoPermissao.setText("Pelo menos uma permissão foi concedida");
                textViewResultadoPermissao.setTextColor(getResources().getColor(R.color.teal_700));
                textViewResultadoPermissao.setGravity(Gravity.CENTER);
            }

        }
    }
}
