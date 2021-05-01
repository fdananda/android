package com.fdananda.gitintent;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTelefone;
    private EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTelefone = findViewById(R.id.editTextTelefone);
        editTextEmail = findViewById(R.id.editTextEmail);
    }

    public void enviarEmail(View view){

        String email = editTextEmail.getText().toString();

        if(email.isEmpty() || email =="" || !email.contains("@")){
            Toast.makeText(this, "Preencha um e-mail válido!", Toast.LENGTH_SHORT).show();
        }else {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Título do e-mail");
            intent.putExtra(Intent.EXTRA_TEXT, "Corpo do e-mail.");
            intent.setType("message/rfc822");
            startActivity(Intent.createChooser(intent, "Enviar e-mail"));
        }
    }

    public void fazerLigacao(View view){

        String numeroTelefone = editTextTelefone.getText().toString();

        if(numeroTelefone.isEmpty() || numeroTelefone ==""){
            Toast.makeText(this, "Preencha um telefone válido!", Toast.LENGTH_SHORT).show();
        }else{

            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+numeroTelefone));
            startActivity(intent);
        }
    }

    public void abrirImagem(View view){

        String imagem = "https://2.bp.blogspot.com/-jdPrk5R6adk/W5VMs0mXsyI/AAAAAAABuOo/EAumZucrKew5ahNBwUFX8CCqWCzz3MvdACLcBGAs/s640/20180410_scooby005.jpg";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(imagem));
            startActivity(intent);
    }

    public void abrirGoogleMaps(View view){

        String endereco = "https://goo.gl/maps/GpB5mQa42LGNBTBt6";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(endereco));
        startActivity(intent);
    }

    public void abrirSite(View view){

        String endereco = "https://github.com/fdananda";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(endereco));
        startActivity(intent);
    }
}
