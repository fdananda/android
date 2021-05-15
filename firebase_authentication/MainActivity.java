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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextSenha, editTextEsqueciSenha;
    private EditText editTextEmailLogar, editTextSenhaLogar;
    private EditText editTextEmailExcluir, editTextSenhaExcluir;
    private TextView textViewMensagemErro, textViewVerificarLogado,
            textViewMensagemErroLogar, textViewMensagemErroExcluir,
            textViewMensagemErroEsqueci;
    String mensagemErro = "";
    private FirebaseAuth autenticacao = Configuracao.getAutenticacao();
    private Usuario usuario;

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

        editTextEsqueciSenha = findViewById(R.id.editTextEmailEsqueci);

        textViewMensagemErro         = findViewById(R.id.textViewMensagemErroCadastrar);
        textViewMensagemErroLogar    = findViewById(R.id.textViewMensagemErroLogar);
        textViewMensagemErroExcluir  = findViewById(R.id.textViewMensagemErroExcluir);
        textViewVerificarLogado      = findViewById(R.id.textViewVerificarLogado);
        textViewMensagemErroEsqueci  = findViewById(R.id.textViewMensagemErroEsqueci);
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

                Usuario usuario = new Usuario();
                usuario.setEmail(email);
                usuario.setSenha(senha);

                //Cadastrar usuário
                autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
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
                                    try {
                                        throw  task.getException();
                                    }catch (FirebaseAuthWeakPasswordException exception){
                                        mensagemErro = "Erro ao cadastrar: Senha não atende aos padrões de segurança!";
                                    }catch (FirebaseAuthInvalidCredentialsException exception){
                                        mensagemErro = "Erro ao cadastrar: E-mail inválido!";
                                    }catch (FirebaseAuthUserCollisionException exception){
                                        mensagemErro = "Erro ao cadastrar: E-mail já utilizado!";
                                    }catch (Exception exception){
                                        mensagemErro = "Erro ao cadastrar: " + exception.getMessage();
                                    }
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

                Usuario usuario = new Usuario();
                usuario.setEmail(email);
                usuario.setSenha(senha);

                //Logar usuário
                autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
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

                            try {
                                throw task.getException();
                            }catch (FirebaseAuthInvalidCredentialsException exception){
                                mensagemErro = "Erro ao logar: E-mail inválido!";
                            }catch (FirebaseAuthInvalidUserException exception){
                                mensagemErro = "Erro ao logar: Usuário não localizado!";
                            }catch (Exception exception){
                                mensagemErro = "Erro ao logar: " + exception.getMessage();
                            }
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

                Usuario usuario = new Usuario();
                usuario.setEmail(email);
                usuario.setSenha(senha);

                //Excluir usuário
                autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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
                            try {
                                throw task.getException();
                            }catch (FirebaseAuthInvalidCredentialsException exception){
                                mensagemErro = "Erro ao excluir: E-mail inválido!";
                            }catch (FirebaseAuthInvalidUserException exception){
                                mensagemErro = "Erro ao excluir: Usuário não localizado!";
                            }catch (Exception exception){
                                mensagemErro = "Erro ao excluir: " + exception.getMessage();
                            }

                            textViewMensagemErroExcluir.setText(mensagemErro);
                        }
                    }
                });
            }
        }
    }

    public void recuperarSenha(View view){

        String email = editTextEsqueciSenha.getText().toString();

         if (email.isEmpty() || email == "" || !email.contains("@")) {

                mensagemErro = "Preencha um e-mail válido!";
                textViewMensagemErroEsqueci.setText(mensagemErro);
            } else {

                Usuario usuario = new Usuario();
                usuario.setEmail(email);

                //Logar usuário
                autenticacao.sendPasswordResetEmail(usuario.getEmail()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        mensagemErro = "Email enviado com sucesso!";
                        textViewMensagemErroEsqueci.setText(mensagemErro);
                    }
                }).addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        mensagemErro = "Erro ao enviar e-mail: " + e.getMessage();
                        textViewMensagemErroEsqueci.setText(mensagemErro);
                    }
                });

        }
    }
}
