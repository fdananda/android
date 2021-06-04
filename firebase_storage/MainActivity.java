package com.fdananda.gitfirebasestorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao = Configuracao.getAutenticacao();
    private FirebaseUser firebaseUser = autenticacao.getCurrentUser();
    private EditText editTextEmailLogar, editTextSenhaLogar;
    private TextView textViewMensagemErroLogar;
    String mensagemErro = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmailLogar = findViewById(R.id.editTextEmailLogar);
        editTextSenhaLogar = findViewById(R.id.editTextSenhaLogar);
        textViewMensagemErroLogar    = findViewById(R.id.textViewMensagemErroLogar);

        if(firebaseUser != null){

            String email = firebaseUser.getEmail();
            String uid = firebaseUser.getUid();

            abrirNovaPagina(email, uid);
        }

        //autenticacao.signOut();
    }

    public void logarUsuario(View view){

        String email = editTextEmailLogar.getText().toString();
        String senha = editTextSenhaLogar.getText().toString();

        if (autenticacao.getCurrentUser() != null){

            Intent intent = new Intent(getApplicationContext(), StorageActivity.class);
            intent.putExtra("email", autenticacao.getCurrentUser().getEmail());
            intent.putExtra("uid", autenticacao.getCurrentUser().getUid());
            startActivity(intent);

        }else{

            if (email.isEmpty() || email == "" || !email.contains("@")) {

                mensagemErro = "Preencha um e-mail válido!";
                textViewMensagemErroLogar.setText(mensagemErro);
            } else {
                if (senha.isEmpty() || senha == "" || senha.length() < 6) {

                    mensagemErro = "Preencha uma senha válida!";
                    textViewMensagemErroLogar.setText(mensagemErro);
                } else {

                    //Logar usuário
                    autenticacao.signInWithEmailAndPassword(email, senha)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if(task.isSuccessful()){
                                        mensagemErro = "Usuário logado com sucesso: " + task.getResult().getUser().getEmail();
                                        textViewMensagemErroLogar.setText(mensagemErro);

                                        String emailTask = task.getResult().getUser().getEmail();
                                        String uidTask = task.getResult().getUser().getUid();

                                        abrirNovaPagina(emailTask, uidTask);


                                    }else{
                                        mensagemErro = "Erro ao logar: \n" + task.getException().getMessage().toString();
                                        textViewMensagemErroLogar.setText(mensagemErro);
                                    }
                                }
                            });
                }
            }
        }
    }

    public void abrirNovaPagina(String email, String uid){

        Intent intent = new Intent(getApplicationContext(), StorageActivity.class);
        intent.putExtra("email", email);
        intent.putExtra("uid", uid);
        startActivity(intent);
    }
}
