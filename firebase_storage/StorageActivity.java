package com.fdananda.gitfirebasestorage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class StorageActivity extends AppCompatActivity {

    private ImageView imagem;
    private ImageView imageFotoResultado;
    private TextView textViewResultado, textViewResultadoExcluir;
    private TextView textViewResultadoCameraGaleria;
    String nomeUnicoArquivo;
    private String[] permissoes = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private static final int SELECAO_CAMERA = 100;
    private static final int SELECAO_GALERIA = 200;
    private Button buttonCamera;
    private Button buttonGaleria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        imagem = findViewById(R.id.imageFotoUpload);
        textViewResultado = findViewById(R.id.textViewResultado);
        textViewResultadoCameraGaleria = findViewById(R.id.textViewResultadoCameraGaleria);
        textViewResultadoExcluir = findViewById(R.id.textViewResultadoExcluir);
        imageFotoResultado = findViewById(R.id.imageFotoResultado);

        buttonCamera  = findViewById(R.id.buttonUploadCamera);
        buttonGaleria = findViewById(R.id.buttonUploadGaleria);

        Permissao.concederPermissao(permissoes, this, 1);

        //Permissao.validarPermissoes(permisssoesNecessarias, this, 1);

    }

    public void fazerUploadImagem(View view){

        //Preparação da imagem:
        //1. Grava imagem na memória
        //2. Recuperar bitmap da imagem que está na memória
        //3. Faz a compressão da imagem passando formato (JPG, PNG), qualidade, ByteArrayOutputStream
        //4. Faz a conversão do ByteArrayOutputStream em uma matriz de bytes

        imagem.setDrawingCacheEnabled(true);
        imagem.buildDrawingCache();

        Bitmap bitmapFoto = imagem.getDrawingCache();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapFoto.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);

        byte[] bytesImagem = byteArrayOutputStream.toByteArray();

        //Envio da imagem para o Firebase:
        //StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        //StorageReference imagens = storageReference.child("nome_da_pasta");

        StorageReference imagens = Configuracao.getStorage();

        nomeUnicoArquivo = UUID.randomUUID().toString();
        StorageReference imagemRef = imagens.child(nomeUnicoArquivo + ".jpeg");

        UploadTask uploadTask = imagemRef.putBytes(bytesImagem);

        uploadTask.addOnFailureListener(StorageActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                textViewResultado.setText("Erro ao fazer upload da imagem: " + e.getMessage().toString());
            }
        }).addOnSuccessListener(StorageActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                imagemRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {

                        Uri enderecoDaImagem = task.getResult();

                        textViewResultado.setText("Sucesso ao fazer upload da imagem.\nEndereço da imagem:\n" + enderecoDaImagem + "\n\n[Clique aqui para fazer o download]");
                        textViewResultado.setTextColor(getResources().getColor(R.color.black));

                        textViewResultado.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                try {

                                    Glide.with(StorageActivity.this).load(enderecoDaImagem).into(imagem);
                                    //Intent intent = new Intent(getApplicationContext(), UploadActivity.class);
                                    //intent.putExtra("endereco", enderecoDaImagem);
                                    //startActivity(intent);

                                    File localFile = File.createTempFile("images", "jpg");
                                    imagemRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                            textViewResultado.setText("Download realizado com sucesso!");
                                            textViewResultado.setTextColor(getResources().getColor(R.color.black));
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            textViewResultado.setText("Erro ao fazer o download!");
                                            textViewResultado.setTextColor(getResources().getColor(R.color.design_default_color_error));
                                        }
                                    });

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                });
            }
        });

        //
    }

    public void deletarImagem(View view){

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference imagens = storageReference.child("nome_da_pasta");
        StorageReference imagemRef = imagens.child(nomeUnicoArquivo + ".jpeg");

        imagemRef.delete().addOnFailureListener(StorageActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                textViewResultadoExcluir.setText("Erro ao excluir: " + e.getMessage());
            }
        }).addOnSuccessListener(StorageActivity.this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                textViewResultadoExcluir.setText("Sucesso ao excluir!");
                textViewResultadoExcluir.setTextColor(getResources().getColor(R.color.black));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            Bitmap bitmapFoto = null;

            try {

                switch (requestCode){
                    case SELECAO_CAMERA:
                        bitmapFoto = (Bitmap) data.getExtras().get("data");
                        nomeUnicoArquivo = UUID.randomUUID().toString();
                        break;
                    case SELECAO_GALERIA:
                        Uri localImagem = data.getData();
                        bitmapFoto = MediaStore.Images.Media.getBitmap(getContentResolver(), localImagem);
                        nomeUnicoArquivo = UUID.randomUUID().toString();
                        break;
                }

                if (bitmapFoto != null){

                    imageFotoResultado.setImageBitmap(bitmapFoto);

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmapFoto.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);

                    byte[] bytesImagem = byteArrayOutputStream.toByteArray();


                    //Envio da imagem para o Firebase:
                    StorageReference imagens = Configuracao.getStorage();
                    StorageReference imagemRef = imagens.child(nomeUnicoArquivo + ".jpeg");

                    UploadTask uploadTask = imagemRef.putBytes(bytesImagem);

                    uploadTask.addOnFailureListener(StorageActivity.this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            textViewResultadoCameraGaleria.setText("Erro ao fazer upload da imagem: " + e.getMessage().toString());
                        }
                    }).addOnSuccessListener(StorageActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            imagemRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {

                                    imageFotoResultado.setImageResource(R.drawable.ic_galeria_cinza);

                                    Uri enderecoDaImagem = task.getResult();

                                    textViewResultadoCameraGaleria.setText("Sucesso ao fazer upload da imagem.\nEndereço da imagem:\n" + enderecoDaImagem + "\n\n[Clique aqui para fazer o download]");
                                    textViewResultadoCameraGaleria.setTextColor(getResources().getColor(R.color.black));

                                    textViewResultadoCameraGaleria.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            try {

                                                Glide.with(StorageActivity.this).load(enderecoDaImagem).into(imageFotoResultado);
                                                File localFile = File.createTempFile("images", "jpg");
                                                imagemRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                                    @Override
                                                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                                        textViewResultadoCameraGaleria.setText("Download realizado com sucesso!");
                                                        textViewResultadoCameraGaleria.setTextColor(getResources().getColor(R.color.black));
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception exception) {
                                                        textViewResultadoCameraGaleria.setText("Erro ao fazer o download!");
                                                        textViewResultadoCameraGaleria.setTextColor(getResources().getColor(R.color.design_default_color_error));
                                                    }
                                                });

                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    });
                                }
                            });
                        }
                    });

                }

            }catch (Exception e){

            }

        }
        
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int permissaoResultado : grantResults){

            if (permissaoResultado == PackageManager.PERMISSION_DENIED){

                textViewResultado.setText("Permissão foi negada. Não é possível usar o app!");

            }

        }
    }


    public void fazerUploadCamera(View view){

        Intent i = new  Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, SELECAO_CAMERA);
    }

    public void fazerUploadGaleria(View view){

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECAO_GALERIA );

    }


}
