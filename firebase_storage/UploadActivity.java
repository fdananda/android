package com.fdananda.gitfirebasestorage;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.google.firebase.storage.StorageReference;

public class UploadActivity extends AppCompatActivity {

    private ImageView imageViewResultadoUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        Bundle dadosRecebidos = getIntent().getExtras();
        String enderecoDaImagem = dadosRecebidos.getString("endereco");

        StorageReference imagens = Configuracao.getStorage();

        imageViewResultadoUpload = findViewById(R.id.imageViewResultado);
        Glide.with(UploadActivity.this).load(enderecoDaImagem).into(imageViewResultadoUpload);
    }
}
