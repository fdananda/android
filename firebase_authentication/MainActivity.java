package com.fdananda.gitfirebaseauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao = FirebaseAuth.getInstance();
    private EditText editTextEmail, editTextSenha;
    private EditText editTextEmailLogar, editTextSenhaLogar;
    private EditText editTextEmailExcluir, editTextSenhaExcluir;
    private TextView textViewMensagemErro, textViewVerificarLogado,
            textViewMensagemErroLogar, textViewMensagemErroExcluir;
    String mensagemErro = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmailCadastrar);
        editTextSenha = findViewById(R.id.editTextSenhaCadastrar);

        editTextEmailLogar = findViewById(R.id.editTextEmailLogar);
        editTextSenhaLogar = findViewById(R.id.editTextSenhaLogar);

        editTextEmailExcluir = findViewById(R.id.editTextEmailExcluir);
        editTextSenhaExcluir = findViewById(R.id.editTextSenhaExcluir);

        textViewMensagemErro         = findViewById(R.id.textViewMensagemErroCadastrar);
        textViewMensagemErroLogar    = findViewById(R.id.textViewMensagemErroLogar);
        textViewMensagemErroExcluir  = findViewById(R.id.textViewMensagemErroExcluir);
        textViewVerificarLogado      = findViewById(R.id.textViewVerificarLogado);
    }


    public void cadastrarUsuario(View view){

        String email = editTextEmail.getText().toString();
        String senha = editTextSenha.getText().toString();

        if (email.isEmpty() || email == "" || !email.contains("@")) {

            mensagemErro = "Preencha um e-mail válido!";
            textViewMensagemErro.setText(mensagemErro);
        } else {
            if (senha.isEmpty() || senha == "" || senha.length() < 6) {

                mensagemErro = "Preencha uma senha válida!";
                textViewMensagemErro.setText(mensagemErro);
            } else {

                //Cadastrar usuário
                autenticacao.createUserWithEmailAndPassword(email, senha)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){

                                    mensagemErro = "Usuário cadastrado com sucesso!";
                                    textViewMensagemErro.setText(mensagemErro);

                                    Intent intent = new Intent(getApplicationContext(), UsuarioLogadoActivity.class);
                                    intent.putExtra("email", task.getResult().getUser().getEmail());
                                    intent.putExtra("uid", task.getResult().getUser().getUid());
                                    startActivity(intent);

                                }else{
                                    mensagemErro = "Erro ao cadastrar: \n" + task.getException().getMessage().toString();
                                    textViewMensagemErro.setText(mensagemErro);
                                }
                            }
                        });
            }
        }
    }

    public void verificarUsuario(View view){

        //Verificar se usuário está logado
        if(autenticacao.getCurrentUser() != null){
            String mensagemErro = "Usuário logado: " + autenticacao.getCurrentUser().getEmail();
            textViewVerificarLogado.setText(mensagemErro);
        }else {
            String mensagemErro = "Usuário não está logado!";
            textViewVerificarLogado.setText(mensagemErro);
        }
    }

    public void logarUsuario(View view){

        String email = editTextEmailLogar.getText().toString();
        String senha = editTextSenhaLogar.getText().toString();

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

                            Intent intent = new Intent(getApplicationContext(), UsuarioLogadoActivity.class);
                            intent.putExtra("email", task.getResult().getUser().getEmail());
                            intent.putExtra("uid", task.getResult().getUser().getUid());
                            startActivity(intent);

                        }else{
                            mensagemErro = "Erro ao logar: \n" + task.getException().getMessage().toString();
                            textViewMensagemErroLogar.setText(mensagemErro);
                        }
                    }
                });
            }
        }
    }

    public void deslogarUsuario(View view){
        autenticacao.signOut();
        Toast.makeText(this, "Usuário foi deslogado!", Toast.LENGTH_SHORT).show();
    }

    public void excluirUsuario(View view){

        String email = editTextEmailExcluir.getText().toString();
        String senha = editTextSenhaExcluir.getText().toString();

        if (email.isEmpty() || email == "" || !email.contains("@")) {

            mensagemErro = "Preencha um e-mail válido!";
            textViewMensagemErroExcluir.setText(mensagemErro);
        } else {
            if (senha.isEmpty() || senha == "" || senha.length() < 6) {

                mensagemErro = "Preencha uma senha válida!";
                textViewMensagemErroExcluir.setText(mensagemErro);
            } else {

                //Excluir usuário
                autenticacao.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            autenticacao.getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        mensagemErro = "Usuário esxcluído com sucesso!";
                                        textViewMensagemErroExcluir.setText(mensagemErro);

                                    }else{
                                        mensagemErro = "Erro ao excluir usuário!";
                                        textViewMensagemErroExcluir.setText(mensagemErro);
                                    }
                                }
                            });

                        }else{
                            mensagemErro = "Erro ao excluir usuário";
                            textViewMensagemErroExcluir.setText(mensagemErro);
                        }
                    }
                });
            }
        }
    }
}
