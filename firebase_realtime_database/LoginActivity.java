package com.fdananda.gitfirebaserealtimedatabase;

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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmailLogar, editTextSenhaLogar;
    private TextView textViewMensagemErroLogar;
    String mensagemErro = "";
    private FirebaseAuth autenticacao = Configuracao.getAutenticacao();
    private FirebaseUser firebaseUser = autenticacao.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmailLogar = findViewById(R.id.editTextEmailLogar);
        editTextSenhaLogar = findViewById(R.id.editTextSenhaLogar);
        textViewMensagemErroLogar = findViewById(R.id.textViewMensagemErroLogar);

        if(firebaseUser != null){

            String email = firebaseUser.getEmail();
            String uid = firebaseUser.getUid();

            abrirNovaPagina(email, uid);
        }

    }

    public void logarUsuario(View view){

        String email = editTextEmailLogar.getText().toString();
        String senha = editTextSenhaLogar.getText().toString();

        if (autenticacao.getCurrentUser() != null) {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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

                Usuario usuario = new Usuario();
                usuario.setEmail(email);
                usuario.setSenha(senha);

                //Logar usuário
                autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    mensagemErro = "Usuário logado com sucesso: " + task.getResult().getUser().getEmail();
                                    textViewMensagemErroLogar.setText(mensagemErro);
                                    textViewMensagemErroLogar.setTextColor(getResources().getColor(R.color.black));

                                    String emailTask = task.getResult().getUser().getEmail();
                                    String uidTask = task.getResult().getUser().getUid();

                                    abrirNovaPagina(emailTask, uidTask);

                                } else {

                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthInvalidCredentialsException exception) {
                                        mensagemErro = "Erro ao logar: E-mail inválido!";
                                    } catch (FirebaseAuthInvalidUserException exception) {
                                        mensagemErro = "Erro ao logar: Usuário não localizado!";
                                    } catch (Exception exception) {
                                        mensagemErro = "Erro ao logar: " + exception.getMessage();
                                    }
                                    textViewMensagemErroLogar.setText(mensagemErro);
                                }
                            }
                        });
            }
            }
        }
    }

    public void abrirNovaPagina(String email, String uid){

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("email", email);
        intent.putExtra("uid", uid);
        startActivity(intent);
    }
}
